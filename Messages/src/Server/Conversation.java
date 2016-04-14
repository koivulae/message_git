package Server;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * {@link Conversation} luokka huolehtii kahden käyttäjän välisestä keskustelusta. 
 */
public class Conversation {
	/*
	 * {@link receiverType} ei ole käytössä, mutta sen avulla pystyttäisiin määrittämään 
	 * vastaanottajan tyyppi.
	 */
	enum receiverType{
		USER,
		GROUP
	};
	
	/*
	 * messages lista sisältää kaikki keskustelun väliset viestit. 
	 */
	private List<Message> messages;
	
	/*
	 * uuid sisältää keskustelun yksiselitteisen tunnisteen.
	 */
	private String uuid;
	
	/*
	 * user1Uuid ja user2Uuid sisältävät keskusteluun liittyvien käyttäjien yksiselitteiset tunnisteet.
	 */
	private String user1Uuid;
	private String user2Uuid;
	
	/*
	 * {@link #Conversation()} on luokan rakentaja, jossa generoidaan uuid ja alustetaan viestit-lista.
	 */
	public Conversation() {
		messages = new ArrayList<Message>();
		uuid = UUID.randomUUID().toString();
	}
	/*
	 * {@link #getUuid()} palauttaa uuid:n.
	 * @return uuid keskustelun tunniste
	 */
	public String getUuid() {
		return uuid;
	}
	/*
	 * {@link #getMessages()} palauttaa keskustelun viesti.
	 * @return messages keskustelun viestit
	 */
	public List<Message> getMessages() {
		return messages;
	}
	/*
	 * {@link #setMessages()} lisää viestin keskusteluun.
	 * @param message viesti
	 */
	public void setMessages(Message message) {
		this.messages.add(message);
	}
	/*
	 * {@link #getUser1Uuid()} palauttaa keskustelun käyttäjän uuid:n.
	 * @return user1Uuid käyttäjän tunniste
	 */
	public String getUser1Uuid() {
		return user1Uuid;
	}
	/*
	 * {@link #setUser1Uuid()} asettaa keskustelun käyttäjän uuid:n.
	 * @param user1Uuid käyttäjän tunniste
	 */
	public void setUser1Uuid(String user1Uuid) {
		this.user1Uuid = user1Uuid;
	}
	/*
	 * {@link #getUser2Uuid()} palauttaa keskustelun käyttäjän uuid:n.
	 * @return user2Uuid käyttäjän tunniste
	 */
	public String getUser2Uuid() {
		return user2Uuid;
	}
	/*
	 * {@link #setUser2Uuid()} asettaa keskustelun käyttäjän uuid:n.
	 * @param user1Uuid käyttäjän tunniste
	 */
	public void setUser2Uuid(String user2Uuid) {
		this.user2Uuid = user2Uuid;
	}
	/*
	 * {@link #getNewestMessageTime()} palauttaa uusimman viestin ajan.
	 * @return messageTime viestin aikaleima
	 */
	public Date getNewestMessageTime()
	{
		Message last = messages.get(messages.size()-1);
		Date messageTime = last.getTimestamp();
		return messageTime;
	}
	/*
	 * {@link #getFirstMessageTime()} palauttaa vanhimman viestin ajan.
	 * @return messageTime viestin aikaleima
	 */
	public Date getFirstMessageTime()
	{
		if (messages.size() > 0)
		{
			Message first = messages.get(0);
			Date messageTime = first.getTimestamp();
			return messageTime;
		}
		return null;
	}
	/*
	 * {@link #getLastMessage()} palauttaa uusimmen keskustelun viestin.
	 * @return message viesti
	 */
	public Message getLastMessage()
	{
		return messages.get(messages.size()-1);
	}
}
