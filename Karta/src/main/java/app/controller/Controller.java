package app.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import app.entities.Karta;
import app.forms.KupovinaKarte_Form;
import app.repository.KartaRepository;

@RestController
@RequestMapping("")
public class Controller {
	
	private KartaRepository kartaRepo;
	
	
	@Autowired
	public Controller(KartaRepository kartaRepo) {
		this.kartaRepo = kartaRepo;
	}
	
	@PostMapping("/kupovinaKarte")
	public ResponseEntity<String> karticaPost(@RequestBody KupovinaKarte_Form kupovinaKarteForm) {
		try {
			
			Karta karta = new Karta();
			
			
			karta.setDatumKupovine(LocalDate.now());
			karta.setIdUser(kupovinaKarteForm.getIdUser());
			karta.setIdLeta(kupovinaKarteForm.getIdLeta());
			
			kartaRepo.save(karta);
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	

}
