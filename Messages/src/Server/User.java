package Server;

import java.util.UUID;

public class User {

	//ID
	//Käyttäjänimi
	private String uuid;
	private String username;
	public User() {
		uuid = UUID.randomUUID().toString();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUuid() {
		return uuid;
	}
	

}
