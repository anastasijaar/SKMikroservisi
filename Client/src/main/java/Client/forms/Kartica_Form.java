package Client.forms;

public class Kartica_Form {

	private String imeVlasnika;
	private String prezimeVlasnika;
	private String brojKartice;
	private String sigurnosniBroj;
	
	public Kartica_Form() {
		
	}
	
	public Kartica_Form(String imeVlasnika, String prezimeVlasnika, String brojKartice, String sigurnosniBroj) {
		super();
		this.imeVlasnika = imeVlasnika;
		this.prezimeVlasnika = prezimeVlasnika;
		this.brojKartice = brojKartice;
		this.sigurnosniBroj = sigurnosniBroj;
	}

	public String getBrojKartice() {
		return brojKartice;
	}
	
	public void setBrojKartice(String brojKartice) {
		this.brojKartice = brojKartice;
	}
	
	public String getSigurnosniBroj() {
		return sigurnosniBroj;
	}
	
	public void setSigurnosniBroj(String sigurnosniBroj) {
		this.sigurnosniBroj = sigurnosniBroj;
	}

	public String getImeVlasnika() {
		return imeVlasnika;
	}

	public void setImeVlasnika(String imeVlasnika) {
		this.imeVlasnika = imeVlasnika;
	}

	public String getPrezimeVlasnika() {
		return prezimeVlasnika;
	}

	public void setPrezimeVlasnika(String prezimeVlasnika) {
		this.prezimeVlasnika = prezimeVlasnika;
	}
}
