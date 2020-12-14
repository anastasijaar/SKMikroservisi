package app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAdmina;
	
	private String username;
	private String password;
	
	public Admin() {
		
	}

	public long getIdAdmina() {
		return idAdmina;
	}

	public void setIdAdmina(long idAdmina) {
		this.idAdmina = idAdmina;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return username + ", " + password;
	}
	
	
	
}
