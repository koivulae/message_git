package Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Server.Server;

/*
 * {@link Client} on asiakas-luokka. Luokka kuuntelee komentoriviä. 
 * Luokka välittää serverille tallennettavat tiedot ja vastaanottaa serveriltä tietoja. Client on vastuussa
 * tulostuksista.
 */
public class Client {
	/*
	 * scanner kuuntelee komentiriviä
	 */
	private static Scanner scanner = new Scanner(System.in);
	/*
	 * server, ohjelman palvelin
	 */
	private static Server server;
	
	/*
	 * {@link main} ohjelman main funktio, joka hoitaa client pään toiminnan. Funktio kuuntelee komentoriviä 
	 * ja kutsuu tarvittaessa apufunktioita. Client kutsuu myös serverin rajapinnan funktioita.
	 */
	public static void main(String[] args) throws Exception {
		server = new Server();
		String senderUuid;
		String sender;
		String conversations;
		String command;
		String order = "normal";
		Map<Integer,List<String>> convs = new HashMap<Integer, List<String>>();

		sender = printStart();  
		senderUuid = server.addUser(sender);

		do 
		{
			System.out.println("KESKUSTELUT (" + sender + ")");
			System.out.println();

			conversations = server.getConversations(order, senderUuid);
			if (!conversations.isEmpty())
			{
				convs = parseConversations(conversations);
			}
			printConversations(convs, sender);
			
			System.out.println("a) Luo uusi keskustelu");
			System.out.println("b) Järjestä");
			System.out.println("c) Vaihda käyttäjää");
			System.out.println("x) Lopeta");
			System.out.print("Komento> ");
			command = scanner.nextLine();
			if ( command.length() == 0 )          
			{
				command = "ö";      
			}

			if (isInteger(command))
			{
				openConversation(command, convs, sender);
			}

			// valitaan operaatio
			switch (command) {
			case "a": startConversation(senderUuid, sender);
			break;

			case "b": order = orderBy(order);
			System.out.println();
			break;

			case "c": 
				sender = printStart();  
				senderUuid = server.addUser(sender);
				order = "normal";
				break;

			default:  

				break;
			}
		} while (!command.equals("x"));

	}
	private static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try
		{
			Integer.parseInt(s);
			isValidInteger = true;
		}
		catch (NumberFormatException ex)
		{
			
		}
		return isValidInteger;
	}
	
	private static void openConversation(String conversationNumber, Map<Integer, List<String>> convs, String sender1)
	{
		String sender = convs.get(Integer.parseInt(conversationNumber)).get(0);
		String receiver = convs.get(Integer.parseInt(conversationNumber)).get(1);
		if (sender1.equals(receiver))
		{
			receiver = sender;
			sender = sender1;
		}
		do
		{
			String messages = server.getUserConversation(sender, receiver);
			String command;

			System.out.println();
			System.out.println("KESKUSTELU henkilön " + receiver + " kanssa");
			System.out.println(messages);
			System.out.print("Viesti> ");
			command = scanner.nextLine();
			if (command.isEmpty())
			{
				break;
			}
			else
			{
				server.selectConversation(sender, receiver);
				server.receiveMessage(command, sender, receiver);
			}
		}while (true);

	}
	
	private static String startConversation(String senderUuid, String sender) throws Exception {

		System.out.println();
		System.out.println("LUO UUSI KESKUSTELU");
		System.out.print("Vastaanottajan nimi> ");
		String receiver = scanner.nextLine();
		String receiverUuid = server.addUser(receiver); 
		if (receiverUuid != "-1")
		{
			server.selectConversation(sender, receiver);
		}
		else
		{
			throw new Exception("Jossain tapahtui jotain.");
		}
		System.out.print("Viesti> ");
		String textmessage = scanner.nextLine(); 
		server.receiveMessage(textmessage, sender, receiver);

		return receiver;
	}
	
	private static String orderBy(String order) {
		String command;
		System.out.println();
		System.out.println("JÄRJESTÄ");
		System.out.println();
		System.out.println("a) Viimeisimmän viestin ajankohdan mukaan");
		System.out.println("b) Keskustelun aloitusajankohdan mukaan");
		System.out.println("c) Keskustelun nimen mukaan");
		System.out.println();
		System.out.print("Komento> ");
		command = scanner.nextLine();
		if ( command.length() == 0 )          
		{
			command = "ö";      
		}
		switch (command) {
		case "a": 
			return "lastMessage";
		case "b": 
			return "startTime";
		case "c": 
			return "user";
		default:  
			return "normal";
		}
	}

	private static Map<Integer, List<String>> parseConversations(String conversations)
	{
		List<String> rows; 
		Map<Integer, List<String>> convs = new HashMap<Integer, List<String>>();
		String conv[] = conversations.split("\n");
		for (int i = 0; i < conv.length ; ++i)
		{
			rows =  new ArrayList<String>();
			String parameters[] = conv[i].split(";");
			for (int j = 1; j < parameters.length; ++j)
			{
				rows.add(parameters[j]);
			}
			convs.put(i+1, rows);
		}
		return convs;
	}
	
	private static void printConversations(Map<Integer,List<String>>convs, String sender1)
	{

		for (Integer key : convs.keySet()) 
		{
			if(convs.get(key).get(1).equals(sender1))
			{
				System.out.println(key + ") " + convs.get(key).get(0) + " (" + convs.get(key).get(2) + "; " + convs.get(key).get(3)+ ")");
			}
			else
			{
				System.out.println(key + ") " + convs.get(key).get(1) + " (" + convs.get(key).get(2) + "; " + convs.get(key).get(3)+ ")");
			}			
		}
		if (convs.size()>0)
		{
			System.out.println();
		}
	}
	
	private static String printStart()
	{
		String sender;
		System.out.print("Anna nimesi> ");
		sender = scanner.nextLine();  
		return sender;
	}
}
