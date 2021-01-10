package Client.forms;

public class RegistrationForm {

	private String ime;
	private String prezime;
	private String email;
	private String password;
	private int predjeneMilje;
	private String brojPasosa;
	

	public RegistrationForm(String ime, String prezime, String email, String password, int predjeneMilje,
			String brojPasosa) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
		this.predjeneMilje = predjeneMilje;
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

	public int getPredjeneMilje() {
		return predjeneMilje;
	}

	public void setPredjeneMilje(int predjeneMilje) {
		this.predjeneMilje = predjeneMilje;
	}

	public String getBrojPasosa() {
		return brojPasosa;
	}

	public void setBrojPasosa(String brojPasosa) {
		this.brojPasosa = brojPasosa;
	}
}