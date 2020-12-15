package app.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kartica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idKartice;

	private String brKartice;
	private String sigurnosniKod;
	private int stanjeNaRacunu;
	//private List<Integer> kupujeKarte;

	@OneToMany(mappedBy = "kartica")
	private List<User_Kartica> korisnici;

	public Kartica() {

	}

	/*public List<Integer> getKupujeKarte() {
		return kupujeKarte;
	}

	public void setKupujeKarte(List<Integer> kupujeKarte) {
		this.kupujeKarte = kupujeKarte;
	}*/

	public int getStanjeNaRacunu() {
		return stanjeNaRacunu;
	}

	public void setStanjeNaRacunu(int stanjeNaRacunu) {
		this.stanjeNaRacunu = stanjeNaRacunu;
	}

	public String getBrKartice() {
		return brKartice;
	}

	public void setBrKartice(String brKartice) {
		this.brKartice = brKartice;
	}

	public String getSigurnosniKod() {
		return sigurnosniKod;
	}

	public void setSigurnosniKod(String sigurnosniKod) {
		this.sigurnosniKod = sigurnosniKod;
	}

	public long getIdKartice() {
		return idKartice;
	}

	public void setIdKartice(int idKartice) {
		this.idKartice = idKartice;
	}

	public List<User_Kartica> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(List<User_Kartica> korisnici) {
		this.korisnici = korisnici;
	}

	@Override
	public String toString() {
		return brKartice + ", " + sigurnosniKod;
	}
	
	

}

