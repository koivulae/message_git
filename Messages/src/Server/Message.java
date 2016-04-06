package Server;

import java.util.Date;
import java.util.UUID;

public class Message {

	private String uuid;
	private String senderUuid;
	private String receiverUuid;
	private String messageText;
	private Date timestamp;
	public Message() {
		uuid = UUID.randomUUID().toString();
		// TODO Auto-generated constructor stub
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSenderUuid() {
		return senderUuid;
	}
	public void setSenderUuid(String senderUuid) {
		this.senderUuid = senderUuid;
	}
	public String getReceiverUuid() {
		return receiverUuid;
	}
	public void setReceiverUuid(String receiverUuid) {
		this.receiverUuid = receiverUuid;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
