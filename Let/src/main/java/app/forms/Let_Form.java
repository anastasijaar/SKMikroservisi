package app.forms;

public class Let_Form {

	private long idLeta;
	private String pocetnaDestinacija;
	private String krajnjaDestinacija;
	private int duzinaLeta;
	private int cena;
	private boolean isCanceled;
	private long idAviona;
	
	public Let_Form(String pocetnaDestinacija, String krajnjaDestinacija, int duzinaLeta, int cena, boolean isCanceled) {
		super();
		this.pocetnaDestinacija = pocetnaDestinacija;
		this.krajnjaDestinacija = krajnjaDestinacija;
		this.duzinaLeta = duzinaLeta;
		this.cena = cena;
		this.isCanceled = isCanceled;
	}
	public long getIdAviona() {
		return idAviona;
	}
	public void setIdAviona(long idAviona) {
		this.idAviona = idAviona;
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
	public long getIdLeta() {
		return idLeta;
	}
	public void setIdLeta(long idLeta) {
		this.idLeta = idLeta;
	}
	
}
