package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * {@link Database} eli tietokanta. 
 */
public class Database {

	/*
	 * messages sis‰lt‰‰ kaikki viestit. T‰ss‰ toteutuksessa turha, mutta jos toteutus korvataan
	 * tietokannalla tulee t‰st‰ luoda erillinen taulu.
	 */
	private List<Message> messages;
	/*
	 * users sis‰lt‰‰ kaikki k‰ytt‰j‰t. Mapista haetaan k‰ytt‰j‰nimen avulla.
	 */
	private Map<String,User> users;
	/*
	 * conversations sis‰lt‰‰ tietokannan keskustelut. 
	 */
	private List<Conversation> conversations;

	/*
	 * {@link #Database()} rakentaja, jossa alustetaan users, conversations ja messages tietorakenteet.
	 */
	public Database() {
		users = new HashMap<String, User>();
		conversations = new ArrayList<Conversation>();
		messages = new ArrayList<Message>();
	}

	/*
	 * {@link #getMessages()} palauttaa kaikki viestit.
	 * @return messages viestit
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/*
	 * {@link #insertMessage()} asettaa viestin.
	 * @param message viesti
	 */
	public void insertMessage(Message message) {
		this.messages.add(message);
	}

	/*
	 * {@link #getUsers()} palauttaa k‰ytt‰j‰t.
	 * @return users k‰ytt‰j‰t
	 */
	public Map<String, User> getUsers() {
		return users;
	}
	
	/*
	 * {@link #insertUser()} asettaa k‰ytt‰j‰n.
	 * @param user k‰ytt‰j‰
	 */
	public void insertUser( User user) {
		this.users.put(user.getUsername(), user);
	}
	/*
	 * {@link #getConversations()} palauttaa k‰ytt‰j‰n keskustelut.
	 * @param senderUuid k‰ytt‰j‰n tunniste
	 * @return convs k‰ytt‰j‰n keskustelut
	 */
	public List<Conversation> getConversations(String senderUuid) {
		List<Conversation> convs = new ArrayList<Conversation>();
		for (Conversation c : conversations)
		{
			if ((c.getUser1Uuid().equals(senderUuid)) || c.getUser2Uuid().equals(senderUuid))
			{
				convs.add(c);
			}
		}
		return convs;
	}

	/*
	 * {@link #getConversations()} palauttaa keskustelut.
	 * @return conversations keskustelut
	 */
	public List<Conversation> getConversations() {
		return conversations;
	}
	/*
	 * {@link #getLastMessageConversations()} palauttaa k‰ytt‰j‰n keskustelut 
	 * viimeisen viestin mukaan j‰rjestettyn‰.
	 * @param senderConvs k‰ytt‰j‰n keskustelut
	 * @return List<Conversation> k‰ytt‰j‰n viestit
	 */
	public List<Conversation> getLastMessageConversations(List<Conversation> senderConvs) {
		List<Conversation> sortedByLastMessage = new ArrayList<Conversation>(senderConvs);
		Collections.sort(sortedByLastMessage, new Comparator<Conversation>() {
			public int compare(Conversation p1, Conversation p2) {
				return Integer.valueOf(p2.getNewestMessageTime().compareTo(p1.getNewestMessageTime()));
			}
		});
		return sortedByLastMessage;
	}
	/*
	 * {@link #getStartTimeConversations()} palauttaa k‰ytt‰j‰n keskustelut 
	 * ensimm‰isen l‰hetetyn viestin mukaan j‰rjestettyn‰.
	 * @param senderConvs k‰ytt‰j‰n keskustelut
	 * @return List<Conversation> k‰ytt‰j‰n viestit
	 */
	public List<Conversation> getStartTimeConversations(List<Conversation> senderConvs) {
		List<Conversation> sortedByStartTime = new ArrayList<Conversation>(senderConvs);
		Collections.sort(sortedByStartTime, new Comparator<Conversation>() {
			public int compare(Conversation p1, Conversation p2) {
				return Integer.valueOf(p1.getFirstMessageTime().compareTo(p2.getFirstMessageTime()));
			}
		});
		return sortedByStartTime;
	}
	/*
	 * {@link #getUserConversations()} palauttaa k‰ytt‰j‰n keskustelut 
	 * k‰ytt‰j‰nimen mukaan j‰rjestettyn‰.
	 * @param senderConvs k‰ytt‰j‰n keskustelut
	 * @return List<Conversation> k‰ytt‰j‰n viestit
	 */
	public List<Conversation> getUserConversations(List<Conversation> senderConvs) 
	{
		List<Conversation> sortedByUser = new ArrayList<Conversation>(senderConvs);
		Collections.sort(sortedByUser, new Comparator<Conversation>() {
			public int compare(Conversation p1, Conversation p2) {
				String username1 = p1.getLastMessage().getReceiver();
				String username2 = p2.getLastMessage().getReceiver();
				return Integer.valueOf(username1.compareTo(username2));
			}
		});
		return sortedByUser;
	}
	/*
	 * {@link #getUserConversation()} palauttaa k‰ytt‰j‰n keskustelun 
	 * @param sender l‰hett‰j‰n k‰ytt‰j‰nimi
	 * @return conversation k‰ytt‰j‰n viestit
	 */
	public Conversation getUserConversation(String sender) 
	{
		for(Conversation conv : conversations)
		{
			for (Message msg : conv.getMessages())
			{
				if(msg.getSender().equals(sender) || msg.getReceiver().equals(sender))
				{
					return conv;
				}
			}
		}
		return null;
	}
	/*
	 * {@link #insertConversation()} asettaa keskustelun. 
	 * @param conversation keskustelu
	 */
	public void insertConversation(Conversation conversation) 
	{
		this.conversations.add(conversation);
	}
	
	/*
	 * {@link #getUser()} palauttaa k‰ytt‰j‰n. 
	 * @param username k‰ytt‰j‰nimi
	 */
	public User getUser(String username)
	{
		return users.get(username);
	}
	/*
	 * {@link #getOldConversation()} palauttaa l‰hett‰j‰n ja vastaanottajan keskustelut. 
	 * @param sender l‰hett‰j‰n k‰ytt‰j‰nimi
	 * @param receiver vastaanottajan k‰ytt‰j‰nimi
	 * @return Conversation keskustelu
	 */
	public Conversation getOldConversation(String sender, String receiver)
	{
		for (Conversation c : conversations)
		{
			if ((c.getLastMessage().getSender().equals(sender) 
					&& c.getLastMessage().getReceiver().equals(receiver)) ||
					(c.getLastMessage().getSender().equals(receiver) 
							&& c.getLastMessage().getReceiver().equals(sender)) )
			{
				return c;
			}
		}
		return null;
	}
}
