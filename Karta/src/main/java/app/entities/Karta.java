package app.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Karta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idKarte;

	private int idUser;
	private int idLeta;
	private LocalDate datumKupovine;

	public int getIdKarte() {
		return idKarte;
	}

	public void setIdKarte(int idKarte) {
		this.idKarte = idKarte;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdLeta() {
		return idLeta;
	}

	public void setIdLeta(int idLeta) {
		this.idLeta = idLeta;
	}

	public LocalDate getDatumKupovine() {
		return datumKupovine;
	}

	public void setDatumKupovine(LocalDate datumKupovine) {
		this.datumKupovine = datumKupovine;
	}

	@Override
	public String toString() {
		return idKarte + ", "+ idLeta + ", "+ datumKupovine;
	}
	
	

}
