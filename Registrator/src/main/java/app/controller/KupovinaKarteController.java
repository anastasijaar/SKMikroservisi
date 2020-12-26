package app.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.forms.KupovinaKarte_Form;


@RestController
public class KupovinaKarteController {

	@PostMapping("/kupovinaKarte")
	public ResponseEntity<String> karticaPost(@RequestBody KupovinaKarte_Form kupovinaKarteForm) {
		try {
			
			long idKartice = kupovinaKarteForm.getIdKartice();
			
			if(idKartice == 0) {
				
				return new ResponseEntity<String>("Morate uneti broj kartice.",HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
