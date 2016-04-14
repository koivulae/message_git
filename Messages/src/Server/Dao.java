package Server;

import java.util.List;

/*
 * {@link Dao} on tietokannan rajapinta, jossa m‰‰ritet‰‰n tietokannan metodit.
 */
public interface Dao {

	public void insertMessage(Message message);
	public void insertUser(User user);
	public void insertConversation(Conversation conversation);
	public List<Conversation> getConversations(String order, String senderUuid);
	public User getUser(String username);
	public Conversation getConversation(String sender, String receiver);
	public Message getNewestMessage(Conversation conversation);

}
