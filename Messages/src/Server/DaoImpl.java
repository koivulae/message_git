package Server;


import java.util.List;

/*
 * {@link DaoImpl} luokka joka toteuttaa Dao:n eli tietokannan rajapinnan.
 */
public class DaoImpl implements Dao {

	/*
	 * database tietokanta
	 */
	private Database database;
	/*
	 * {@link #DaoImpl()} rakentaja. Alustaa tietokannan.
	 */
	public DaoImpl() {
		database = new Database();
	}
	/*
	 * {@link #insertMessage()} asettaa viestin. 
	 * @param message viesti
	 */
	public void insertMessage(Message message)
	{
		database.insertMessage(message);
	}
	/*
	 * {@link #insertUser()} asettaa k�ytt�j�n. 
	 * @param user k�ytt�j�
	 */
	public void insertUser(User user)
	{
		database.insertUser(user);
	}
	/*
	 * {@link #insertConversation()} asettaa keskustelun. 
	 * @param conversation keskustelu 
	 */
	public void insertConversation(Conversation conversation)
	{
		for (Conversation c : database.getConversations())
		{
			if (c.getUuid().toString().equals(conversation.getUuid().toString()))
			{
				return;
			}
		}
		database.insertConversation(conversation);
		return;
	}
	/*
	 * {@link #getConversations()} palauttaa j�rjestetyn keskustelun. 
	 * @param order j�rjestys
	 * @param senderUuid l�hett�j�n tunniste
	 * @return conversation keskustelu
	 */
	public List<Conversation> getConversations(String order, String senderUuid)
	{
		List<Conversation> senderConvs = database.getConversations(senderUuid);
		if (order.equals("startTime"))
		{
			return database.getStartTimeConversations(senderConvs);
		}
		else if (order.equals("user"))
		{
			return database.getUserConversations( senderConvs);
		}
		else if (order.equals("lastMessage"))
		{
			return database.getLastMessageConversations(senderConvs);
		}	
		return senderConvs;
	}
	/*
	 * {@link #getUser()} palauttaa k�ytt�j�n. 
	 * @param username k�ytt�j�nimi l�hett�j�n k�ytt�j�nimi
	 * @return user k�ytt�j� 
	 */
	public User getUser(String username)
	{
		return database.getUser(username);
	}
	/*
	 * {@link #getConversation()} palauttaa keskustelun. 
	 * @param sender l�hett�j�n k�ytt�j�nimi
	 * @param receiver vastaanottajan k�ytt�j�nimi
	 * @return conversation keskustelu 
	 */
	public Conversation getConversation(String sender, String receiver)
	{
		return database.getOldConversation(sender, receiver);
	}
	/*
	 * {@link #getNewestMessage()} palauttaa keskustelun uusimman viestin. 
	 * @param conversation keskustelu
	 * @return message viesti 
	 */
	public Message getNewestMessage(Conversation conversation)
	{
		Message lastMessage = conversation.getMessages().get(conversation.getMessages().size()-1);
		return lastMessage;
	}

}
