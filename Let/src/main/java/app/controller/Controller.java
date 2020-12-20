package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Avion;
import app.forms.Avion_Form;
import app.repository.AvionRepository;

@RestController
public class Controller {
	
	private AvionRepository avionRepo;
	
	@Autowired
	public Controller(AvionRepository avionRepo) {
		this.avionRepo = avionRepo;
	}
	
	@PostMapping("/avion")
	public ResponseEntity<String> avionPost(@RequestBody Avion_Form avionForm) {
		try {
			
			Avion avion = new Avion(avionForm.getNazivAviona(), avionForm.getKapacitet(), avionForm.getTrenutnoPutnika());
			
			avionRepo.save(avion);
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			System.out.println("error 123");
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
