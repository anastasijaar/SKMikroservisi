package app.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Avion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAviona;
	
	private String nazivAviona;
	private int kapacitet;
	private int trenutnoPutnika;
	
	@OneToMany(mappedBy = "avion")
    private List<Let> letovi;
	
	public Avion() {
		
	}

	public Avion(String nazivAviona, int kapacitet, int trenutnoPutnika) {
		this.nazivAviona = nazivAviona;
		this.kapacitet = kapacitet;
		this.trenutnoPutnika = trenutnoPutnika;
	}

	public int getIdAviona() {
		return idAviona;
	}

	public void setIdAviona(int idAviona) {
		this.idAviona = idAviona;
	}

	public String getNazivAviona() {
		return nazivAviona;
	}

	public void setNazivAviona(String nazivAviona) {
		this.nazivAviona = nazivAviona;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public int getTrenutnoPutnika() {
		return trenutnoPutnika;
	}

	public void setTrenutnoPutnika(int trenutnoPutnika) {
		this.trenutnoPutnika = trenutnoPutnika;
	}

	public List<Let> getLetovi() {
		return letovi;
	}

	public void setLetovi(List<Let> letovi) {
		this.letovi = letovi;
	}

	@Override
	public String toString() {
		return nazivAviona + ", " + kapacitet + ", " + trenutnoPutnika;
	}
}
