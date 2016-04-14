package Server;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/*
 * {@link Server} luokka huolehtii clientin ja tietokannan v‰lisest‰ kommunikoinnista. 
 */
public class Server {
	/*
	 * dao tarjoaa tietokannan rajapinnan palvelut
	 */
	private Dao dao;
	/*
	 * conversation sis‰lt‰‰ keskustelun
	 */
	private Conversation conversation;
	
	/*
	 * {@link #Server()} rakentaja, jossa alustetaan tietokannan rajapinta.
	 */
	public Server() {
		dao = new DaoImpl();
	}
	/*
	 * {@link #receiveMessage(String, String, String)} vastaanottaa viestin clientilta.
	 * @param message viesti
	 * @param sender l‰hett‰j‰n k‰ytt‰j‰nimi
	 * @param receiver vastaanottajan k‰ytt‰j‰nimi
	 */
	
	public void receiveMessage(String message, String sender, String receiver )
	{
		Message msg = new Message();
		String receiverUuid = dao.getUser(receiver).getUuid();
		String senderUuid = dao.getUser(sender).getUuid();
		msg.setMessageText(message);
		msg.setReceiver(receiver);
		msg.setSender(sender);
		dao.insertMessage(msg);
		this.conversation.setMessages(msg);
		this.conversation.setUser1Uuid(receiverUuid);
		this.conversation.setUser2Uuid(senderUuid);
		if (dao.getConversation(senderUuid, receiverUuid) == null)
		{
			dao.insertConversation(this.conversation);	
		}
	}
	/*
	 * {@link #selectConversation(String sender, String receiver)} valitsee oikean keskustelun.
	 * @param sender l‰hett‰j‰n k‰ytt‰j‰nimi
	 * @param receiver vastaanottajan k‰ytt‰j‰nimi
	 */
	
	public void selectConversation(String sender, String receiver)
	{
		if (dao.getConversation(sender, receiver) == null)
		{
			this.conversation = new Conversation();
		}
		else
		{
			this.conversation = dao.getConversation(sender, receiver);
		}

	}
	/*
	 * {@link #addUser()} lis‰‰ k‰ytt‰j‰n.
	 * @param username k‰ytt‰j‰nimi
	 * @return userUuid k‰ytt‰j‰n uuid 
	 */
	public String addUser(String username)
	{
		User user;
		if (dao.getUser(username) == null)
		{	
			user = new User();
			user.setUsername(username);
			dao.insertUser(user);
			return user.getUuid();	
		}
		else
		{
			user = dao.getUser(username);
			return user.getUuid();	
		}
	}
	/*
	 * {@link #getConversations()} palauttaa l‰hett‰j‰n viestit j‰rjestettyn‰.
	 * @param order j‰rjestys
	 * @param senderUuid l‰hett‰j‰n tunniste
	 * @return conversationmessages keskustelun viestit 
	 */
	public String getConversations(String order, String senderUuid)
	{
		List<Conversation> conv = dao.getConversations(order, senderUuid);
		String message = "";
		int number = 1;
		for(Conversation c : conv)
		{
			Message lastMessage = dao.getNewestMessage(c);
			String receiver = lastMessage.getReceiver();
			String sender = lastMessage.getSender();
			Date timestamp = lastMessage.getTimestamp();
			DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			String time = targetFormat.format(timestamp);
			message += number + ";" + sender + ";" + receiver + ";" + time + ";" + lastMessage.getMessageText() + "\n";
			++number;
		}
		return message;
	}
	/*
	 * {@link #getUserConversation()} palauttaa l‰hett‰j‰n ja vastaanottajan v‰liset viestit.
	 * @param sender l‰hett‰j‰n k‰ytt‰j‰nimi
	 * @param receiver vastaanottajan k‰ytt‰j‰nimi
	 * @return conversationmessages keskustelun viestit 
	 */
	public String getUserConversation(String sender, String receiver)
	{
		Conversation conv = dao.getConversation(sender, receiver);
		String message = "";
		for(Message msg : conv.getMessages())
		{
			Date timestamp = msg.getTimestamp();
			DateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			String time = targetFormat.format(timestamp);
			message += time + ", " + msg.getSender() + ": " + msg.getMessageText() + "\n";
		}
		return message;
	}

}
