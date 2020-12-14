package app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class User_Kartica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;

	@ManyToOne
	@JoinColumn(name = "idKartice")
	private Kartica kartica;

	public User_Kartica() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Kartica getKartica() {
		return kartica;
	}

	public void setKartica(Kartica kartica) {
		this.kartica = kartica;
	}

	@Override
	public String toString() {
		return user + ", " + kartica;
	}
	
	

}
