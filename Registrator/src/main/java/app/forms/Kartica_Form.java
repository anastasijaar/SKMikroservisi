package app.forms;

public class Kartica_Form {

	private String brojKartice;
	private String sigurnosniBroj;
	private int stanjeNaRacunu;
	
	public int getStanjeNaRacunu() {
		return stanjeNaRacunu;
	}
	
	public void setStanjeNaRacunu(int stanjeNaRacunu) {
		this.stanjeNaRacunu = stanjeNaRacunu;
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
}
