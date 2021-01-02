package app.forms;

public class Avion_Form {

	private long idAviona;
	private String nazivAviona;
	private int kapacitet;
	private int trenutnoPutnika;

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

	public long getIdAviona() {
		return idAviona;
	}

	public void setIdAviona(long idAviona) {
		this.idAviona = idAviona;
	}

}
