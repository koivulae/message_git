package Server;

import java.util.Date;
import java.util.UUID;

/*
 * {@link Message} on viesti luokka. 
 */
public class Message {
	/*
	 * {@link messageType} ei ole käytössä, mutta sen avulla pystyttäisiin määrittämään 
	 * viestin tyyppi.
	 */
	enum messageType{
		TEXT,
		IMAGE,
		VIDEO
	};
	/*
	 * uuid viestin yksiselitteinen tunniste
	 */
	private String uuid;
	/*
	 * messageText viestin sisältö
	 */
	private String messageText;
	/*
	 * timestamp viestin lähetysaika
	 */
	private Date timestamp;
	/*
	 * sender viestin lähettäjän käyttäjänimi
	 */
	private String sender;
	/*
	 * receiver viestin saajan käyttäjänimi
	 */
	private String receiver;
	/*
	 * {@link #Message} rakentajassa alustetaan viestin tunniste ja viestin lähetysaika.
	 */
	public Message() {
		uuid = UUID.randomUUID().toString();
		timestamp = new java.util.Date();
	}
	/*
	 * {@link #getUuid()} palauttaa viestin uuid:n.
	 * @return uuid tunniste
	 */
	public String getUuid() {
		return uuid;
	}
	/*
	 * {@link #setUuid()} asettaa viestin uuid:n. 
	 * @param uuid tunniste
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/*
	 * {@link #getSender()} palauttaa viestin lähettäjän. 
	 * @return sender lähettäjän käyttäjänimi
	 */
	public String getSender() {
		return sender;
	}
	/*
	 * {@link #setSender()} asettaa viestin lähettäjän. 
	 * @param sender lähettäjän käyttäjänimi
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/*
	 * {@link #getReceiver()} palauttaa viestin vastaanottajan. 
	 * @return receiver vastaanottajan käyttäjänimi
	 */
	public String getReceiver() {
		return receiver;
	}
	/*
	 * {@link #setReceiver()} asettaa viestin vastaanottajan. 
	 * @param receiver vastaanottajan käyttäjänimi
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	/*
	 * {@link #getMessageText()} palauttaa viestin tekstin. 
	 * @return messageText viestin sisältö
	 */
	public String getMessageText() {
		return messageText;
	}
	/*
	 * {@link #setMessageText()} asettaa viestin tekstin. 
	 * @param messageText viestin sisältö
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	/*
	 * {@link #getTimestamp()} palauttaa viestin lähetysajan. 
	 * @return timestamp viestin lähetysaika
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	/*
	 * {@link #setTimestamp()} asettaa viestin lähetysajan. 
	 * @param timestamp viestin lähetysaika
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
