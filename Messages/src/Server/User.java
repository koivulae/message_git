package Server;

import java.util.UUID;

/*
 * {@link User} on käyttäjä-luokka.
 */
public class User {

	/*
	 * Uuid on käyttäjän yksiselitteinen tunniste.
	 */
	private String uuid;
	/*
	 * username eli käyttäjänimi.
	 */
	private String username;
	/*
	 * {@link #User()} on luokan rakentaja, joka alustaa käyttäjän uuid:n.  
	 */
	public User() {
		uuid = UUID.randomUUID().toString();
	}
	/*
	 * {@link #getUsername()} palauttaa käyttäjänimen.
	 * @return username käyttäjänimi
	 */
	public String getUsername() {
		return username;
	}
	/*
	 * {@link #setUsername()} asettaa käyttäjänimen.
	 * @param username käyttäjänimi
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/*
	 * {@link #getUuid()} palauttaa uuid:n.
	 * @return uuid käyttäjätunniste
	 */
	public String getUuid() {
		return uuid;
	}


}
