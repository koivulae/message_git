package Server;

import java.util.Date;
import java.util.UUID;

/*
 * {@link Message} on viesti luokka. 
 */
public class Message {
	/*
	 * {@link messageType} ei ole k�yt�ss�, mutta sen avulla pystytt�isiin m��ritt�m��n 
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
	 * messageText viestin sis�lt�
	 */
	private String messageText;
	/*
	 * timestamp viestin l�hetysaika
	 */
	private Date timestamp;
	/*
	 * sender viestin l�hett�j�n k�ytt�j�nimi
	 */
	private String sender;
	/*
	 * receiver viestin saajan k�ytt�j�nimi
	 */
	private String receiver;
	/*
	 * {@link #Message} rakentajassa alustetaan viestin tunniste ja viestin l�hetysaika.
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
	 * {@link #getSender()} palauttaa viestin l�hett�j�n. 
	 * @return sender l�hett�j�n k�ytt�j�nimi
	 */
	public String getSender() {
		return sender;
	}
	/*
	 * {@link #setSender()} asettaa viestin l�hett�j�n. 
	 * @param sender l�hett�j�n k�ytt�j�nimi
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	/*
	 * {@link #getReceiver()} palauttaa viestin vastaanottajan. 
	 * @return receiver vastaanottajan k�ytt�j�nimi
	 */
	public String getReceiver() {
		return receiver;
	}
	/*
	 * {@link #setReceiver()} asettaa viestin vastaanottajan. 
	 * @param receiver vastaanottajan k�ytt�j�nimi
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	/*
	 * {@link #getMessageText()} palauttaa viestin tekstin. 
	 * @return messageText viestin sis�lt�
	 */
	public String getMessageText() {
		return messageText;
	}
	/*
	 * {@link #setMessageText()} asettaa viestin tekstin. 
	 * @param messageText viestin sis�lt�
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	/*
	 * {@link #getTimestamp()} palauttaa viestin l�hetysajan. 
	 * @return timestamp viestin l�hetysaika
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	/*
	 * {@link #setTimestamp()} asettaa viestin l�hetysajan. 
	 * @param timestamp viestin l�hetysaika
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
