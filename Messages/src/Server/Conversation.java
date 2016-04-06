package Server;

import java.util.List;

public class Conversation {

	private List<Message> messages;
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(Message message) {
		this.messages.add(message);
	}
	public Conversation() {
		// TODO Auto-generated constructor stub
	}

}
