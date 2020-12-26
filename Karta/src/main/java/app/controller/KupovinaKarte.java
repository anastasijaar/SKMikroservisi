package app.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import app.entities.Karta;
import app.forms.KupovinaKarte_Form;
import app.repository.KartaRepository;
import app.utils.UtilsMethods;

@RestController
public class KupovinaKarte {
	
	private KartaRepository kartaRepo;
	
	
	@Autowired
	public KupovinaKarte(KartaRepository kartaRepo) {
		this.kartaRepo = kartaRepo;
	}
	
	@PostMapping("/kupovinaKarte")
	public ResponseEntity<String> karticaPost(@RequestBody KupovinaKarte_Form kupovinaKarteForm) {
		try {
			
//			Karta karta = new Karta();
			
			ResponseEntity<String> response = UtilsMethods.sendPost("http://localhost:8080/kupovinaKarte", kupovinaKarteForm);
			
//			karta.setDatumKupovine(LocalDate.now());
//			karta.setIdUser(kupovinaKarteForm.getIdUser());
//			karta.setIdLeta(kupovinaKarteForm.getIdLeta());
		
//			kartaRepo.save(karta);
			
			return response;
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
