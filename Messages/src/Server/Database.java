package Server;

import java.util.List;
import java.util.Map;

public class Database {

	private List<Message> messages;
	private Map<String,User> users;
	private List<Conversation> conversations;
	public Database() {
		// TODO Auto-generated constructor stub
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	//Add a new message
	public void setMessage(Message message) {
		this.messages.add(message);
	}
	
	public Map<String, User> getUsers() {
		return users;
	}
	public void setUsers(Map<String, User> users) {
		this.users = users;
	}
	
	//Add a new user
	public void setUser(String uuid, User user) {
		this.users.put(uuid, user);
	}
	public List<Conversation> getConversations() {
		return conversations;
	}
	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}

}
