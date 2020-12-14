package app.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUser;

	private String ime;
	private String prezime;
	private String email;
	private String password;
	private int predjeneMilje;
	private String brojPasosa;
	
	@OneToMany(mappedBy = "user")
    private List<User_Kartica> kartice;
    
    @ManyToOne
    @JoinColumn(name = "idRank")
    private Rank rank;

	public User() {

	}
	

	public User(String ime, String prezime, String email, String password, int predjeneMilje,
			String brojPasosa) {
		
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
		this.predjeneMilje = predjeneMilje;
		this.brojPasosa = brojPasosa;
	}

	public long getId() {
		return idUser;
	}

	public void setId(long id) {
		this.idUser = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPredjeneMilje() {
		return predjeneMilje;
	}

	public void setPredjeneMilje(int predjeneMilje) {
		this.predjeneMilje = predjeneMilje;
	}

	public String getBrojPasosa() {
		return brojPasosa;
	}

	public void setBrojPasosa(String brojPasosa) {
		this.brojPasosa = brojPasosa;
	}

	
	public long getIdUser() {
		return idUser;
	}


	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}


	public List<User_Kartica> getKartice() {
		return kartice;
	}


	public void setKartice(List<User_Kartica> kartice) {
		this.kartice = kartice;
	}


	public Rank getRank() {
		return rank;
	}


	public void setRank(Rank rank) {
		this.rank = rank;
	}


	@Override
	public String toString() {
		return ime + ", " + prezime + ", " + email + ", " + predjeneMilje;
	}
	
	

}