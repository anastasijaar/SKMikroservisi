package app.controller;

import static app.security.SecurityConstants.HEADER_STRING;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.forms.Let_Form;
import app.repository.AvionRepository;

@RestController
public class KapacitetPutnikaController {
	
	private AvionRepository avionRepo;

	public KapacitetPutnikaController(AvionRepository avionRepo) {
		this.avionRepo = avionRepo;
	}
	
	@PostMapping("/proveraPutnika")
	public ResponseEntity<Boolean> proveraPutnikaPost(@RequestBody Let_Form letForm) {
		
		try {
			
			long idAviona = letForm.getIdAviona();
			int kapacitet = avionRepo.getKapacitetPutnika(idAviona);
			System.out.println("Kapacitet je: "+kapacitet);
			if(kapacitet > 0) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
