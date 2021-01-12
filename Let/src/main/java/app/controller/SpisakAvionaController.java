package app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Avion;
import app.forms.Avion_Form;
import app.forms.SpisakAviona;
import app.repository.AvionRepository;

@RestController
public class SpisakAvionaController {

	
	private AvionRepository avionRepo;
	
	public SpisakAvionaController(AvionRepository avionRepo) {
		this.avionRepo = avionRepo;
	}
	
	@GetMapping("/spisakAviona")
	public ResponseEntity<SpisakAviona>spisakLetova(){
		
		try {
			SpisakAviona av = new SpisakAviona();
			
			List<Avion> avion = avionRepo.findAll();
			for (Avion a : avion) {
				Avion_Form af = new Avion_Form(a.getNazivAviona(), a.getKapacitet(), a.getTrenutnoPutnika(), a.isCancled());
				av.getSpisakAviona().add(af);
			}
			
			return new ResponseEntity<>(av, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
