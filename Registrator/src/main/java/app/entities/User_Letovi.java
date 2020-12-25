package app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class User_Letovi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUserLet;
	
	private long idLeta;
	
	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;

	public User_Letovi(long idUserLet, long idLeta, User user) {
		super();
		this.idUserLet = idUserLet;
		this.idLeta = idLeta;
		this.user = user;
	}

	public long getIdUserLet() {
		return idUserLet;
	}

	public void setIdUserLet(long idUserLet) {
		this.idUserLet = idUserLet;
	}

	public long getIdLeta() {
		return idLeta;
	}

	public void setIdLeta(long idLeta) {
		this.idLeta = idLeta;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
