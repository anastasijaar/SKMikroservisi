package Client.entities;

import java.util.List;


public class Avioni {
	
	private long idAviona;
	
	private String nazivAviona;
	private int kapacitet;
	private int trenutnoPutnika;
	private boolean isCancled;
	
	
    private List<Letovi> letovi;
	
	public Avioni() {
		
	}

	public Avioni(String nazivAviona, int kapacitet, int trenutnoPutnika) {
		this.nazivAviona = nazivAviona;
		this.kapacitet = kapacitet;
		this.trenutnoPutnika = trenutnoPutnika;
		this.isCancled = false;
	}

	public boolean isCancled() {
		return isCancled;
	}

	public void setCancled(boolean isCancled) {
		this.isCancled = isCancled;
	}

	public long getIdAviona() {
		return idAviona;
	}

	public void setIdAviona(long idAviona) {
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

	public List<Letovi> getLetovi() {
		return letovi;
	}

	public void setLetovi(List<Letovi> letovi) {
		this.letovi = letovi;
	}

	@Override
	public String toString() {
		return nazivAviona + ", " + kapacitet + ", " + trenutnoPutnika;
	}
}
