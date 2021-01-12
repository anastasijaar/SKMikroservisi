package Client.forms;

public class Avion_Form {

	private long idAviona;
	private String nazivAviona;
	private int kapacitet;
	private int trenutnoPutnika;
	private boolean canceled;

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
	
	public boolean getCanceled() {
		return canceled;
	}

	@Override
	public String toString() {
		return nazivAviona + ", " + kapacitet
				+ ", " + trenutnoPutnika + ", " + canceled ;
	}
}
