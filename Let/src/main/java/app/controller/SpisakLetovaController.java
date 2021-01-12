package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Let;
import app.forms.Let_Form;
import app.forms.SpisakLetova;
import app.repository.LetRepository;

@RestController
public class SpisakLetovaController {

	
	private LetRepository letRepo;
	
	public SpisakLetovaController(LetRepository letRepo) {
		this.letRepo = letRepo;
	}
	
	@GetMapping("/spisakLetova")
	public ResponseEntity<SpisakLetova>spisakLetova(){
		
		try {
			SpisakLetova sp = new SpisakLetova();
			//List<Let> let = letRepo.selectAllFlightWithFreeSpace();
			List<Let> let = letRepo.findAll();
			for (Let l : let) {
				Let_Form lf = new Let_Form(l.getPocetnaDestinacija(), l.getKrajnjaDestinacija(), l.getDuzinaLeta(), l.getCena(), l.isCanceled());
				sp.getSpisakLetova().add(lf);
			}
			
			return new ResponseEntity<>(sp, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
