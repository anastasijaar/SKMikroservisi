package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Let;
import app.forms.Let_Form;
import app.repository.LetRepository;

@RestController
public class SpisakLetovaController {

	
	private LetRepository letRepo;
	
	public SpisakLetovaController(LetRepository letRepo) {
		this.letRepo = letRepo;
	}
	
	@GetMapping("/spisakLetova")
	public ResponseEntity<List<Let>>spisakLetova(){
		
		try {
			List<ResponseEntity<Let_Form>> responseEntiteti = new ArrayList<ResponseEntity<Let_Form>>();
			
			//List<Let> let = letRepo.selectAllFlightWithFreeSpace();
			List<Let> let = letRepo.findAll();
			
			
			return new ResponseEntity<>(let, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
