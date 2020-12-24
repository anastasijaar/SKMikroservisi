package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Avion;
import app.entities.Let;
import app.forms.Avion_Form;
import app.repository.AvionRepository;
import app.repository.LetRepository;

@RestController
public class Controller {
	
	private AvionRepository avionRepo;
	
	private LetRepository letRepo;
	
	@Autowired
	public Controller(AvionRepository avionRepo, LetRepository letRepo) {
		this.avionRepo = avionRepo;
		this.letRepo = letRepo;
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
	
	@GetMapping("/spisakLetova")
	public ResponseEntity<String> spisakLetova(){
		
		try {
			
			List<Let> let = letRepo.selectAllFlightWithFreeSpace();
			for (Let l : let) {
				System.out.println(l.getPocetnaDestinacija() + " " + l.getKrajnjaDestinacija());
			}
			
			return new ResponseEntity<>("sve ok", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
