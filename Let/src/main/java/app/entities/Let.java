package app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Let {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLeta;
	
	private String pocetnaDestinacija;
	private String krajnjaDestinacija;
	private int duzinaLeta;
	private int cena;
	private boolean isCanceled;
	
	@ManyToOne
	@JoinColumn(name = "idAviona")
	private Avion avion;
	
	public Let() {
	
	}

	public Let(String pocetnaDestinacija, String krajnjaDestinacija, int duzinaLeta, int cena,
			boolean isCanceled) {
		super();
		this.pocetnaDestinacija = pocetnaDestinacija;
		this.krajnjaDestinacija = krajnjaDestinacija;
		this.duzinaLeta = duzinaLeta;
		this.cena = cena;
		this.isCanceled = isCanceled;
	}

	public int getIdLeta() {
		return idLeta;
	}

	public void setIdLeta(int idLeta) {
		this.idLeta = idLeta;
	}

	public String getPocetnaDestinacija() {
		return pocetnaDestinacija;
	}

	public void setPocetnaDestinacija(String pocetnaDestinacija) {
		this.pocetnaDestinacija = pocetnaDestinacija;
	}

	public String getKrajnjaDestinacija() {
		return krajnjaDestinacija;
	}

	public void setKrajnjaDestinacija(String krajnjaDestinacija) {
		this.krajnjaDestinacija = krajnjaDestinacija;
	}

	public int getDuzinaLeta() {
		return duzinaLeta;
	}

	public void setDuzinaLeta(int duzinaLeta) {
		this.duzinaLeta = duzinaLeta;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public boolean isCanceled() {
		return isCanceled;
	}

	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	@Override
	public String toString() {
		return pocetnaDestinacija + ", " + krajnjaDestinacija + ", " + cena;
	}
}
