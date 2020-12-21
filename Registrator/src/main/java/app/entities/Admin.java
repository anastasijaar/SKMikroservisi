package app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAdmina;
	
	private String email;
	private String password;
	
	public Admin() {
		
	}

	public int getIdAdmina() {
		return idAdmina;
	}

	public void setIdAdmina(int idAdmina) {
		this.idAdmina = idAdmina;
	}

	public String getUsername() {
		return email;
	}

	public void setUsername(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return email + ", " + password;
	}
}
