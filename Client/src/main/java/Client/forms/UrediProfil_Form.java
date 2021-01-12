package Client.forms;

public class UrediProfil_Form {

	private String ime;
	private String prezime;
	private String email;
	private String password;
	private String brojPasosa;
	
	public UrediProfil_Form() {
		
	}
	
	public UrediProfil_Form(String ime, String prezime, String email, String password, String brojPasosa) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
		this.brojPasosa = brojPasosa;
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
	public String getBrojPasosa() {
		return brojPasosa;
	}
	public void setBrojPasosa(String brojPasosa) {
		this.brojPasosa = brojPasosa;
	}
}
