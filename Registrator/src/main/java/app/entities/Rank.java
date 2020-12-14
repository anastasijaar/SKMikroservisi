package app.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Rank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRank;
	
	private String imeRanka;
	
	@OneToMany(mappedBy = "rank")
    private List<User> korisnici;
	
	
	public Rank() {
		
	}

	public long getId() {
		return idRank;
	}

	public void setId(long idRank) {
		this.idRank = idRank;
	}

	public String getImeRanka() {
		return imeRanka;
	}

	public void setImeRanka(String imeRanka) {
		this.imeRanka = imeRanka;
	}

	public long getIdRank() {
		return idRank;
	}

	public void setIdRank(long idRank) {
		this.idRank = idRank;
	}

	public List<User> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(List<User> korisnici) {
		this.korisnici = korisnici;
	}
	
	

}
