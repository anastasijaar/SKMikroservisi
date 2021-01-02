package app.controller;


import static app.security.SecurityConstants.HEADER_STRING;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;

import app.entities.Karta;
import app.forms.KupovinaKarte_Form;
import app.forms.Let_Form;
import app.repository.KartaRepository;
import app.utils.UtilsMethods;

@RestController
public class KupovinaKarte {
	
	private KartaRepository kartaRepo;
	
	
	@Autowired
	public KupovinaKarte(KartaRepository kartaRepo) {
		this.kartaRepo = kartaRepo;
	}
	
	@GetMapping("/kupovinaKarte")
	public ResponseEntity<String> karticaGet(@RequestHeader(value = HEADER_STRING) HttpHeaders token, @RequestBody Let_Form let_Form) {
		System.out.println("Udje ovdjeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		try {
			long idAviona = let_Form.getIdAviona();
			
			HttpHeaders headers = token;
			
			System.out.println("Header je: "+ headers);
			ResponseEntity<String> response = UtilsMethods.sendGet("http://localhost:8080/kupovinaKarte", headers);

			return response;
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
