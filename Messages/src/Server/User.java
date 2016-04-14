package Server;

import java.util.UUID;

/*
 * {@link User} on k�ytt�j�-luokka.
 */
public class User {

	/*
	 * Uuid on k�ytt�j�n yksiselitteinen tunniste.
	 */
	private String uuid;
	/*
	 * username eli k�ytt�j�nimi.
	 */
	private String username;
	/*
	 * {@link #User()} on luokan rakentaja, joka alustaa k�ytt�j�n uuid:n.  
	 */
	public User() {
		uuid = UUID.randomUUID().toString();
	}
	/*
	 * {@link #getUsername()} palauttaa k�ytt�j�nimen.
	 * @return username k�ytt�j�nimi
	 */
	public String getUsername() {
		return username;
	}
	/*
	 * {@link #setUsername()} asettaa k�ytt�j�nimen.
	 * @param username k�ytt�j�nimi
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/*
	 * {@link #getUuid()} palauttaa uuid:n.
	 * @return uuid k�ytt�j�tunniste
	 */
	public String getUuid() {
		return uuid;
	}


}
