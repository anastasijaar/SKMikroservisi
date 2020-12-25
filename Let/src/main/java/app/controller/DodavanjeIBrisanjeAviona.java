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
import app.forms.Let_Form;
import app.repository.AvionRepository;
import app.repository.LetRepository;

@RestController
public class DodavanjeIBrisanjeAviona {
	
	private AvionRepository avionRepo;
	
	private LetRepository letRepo;
	
	@Autowired
	public DodavanjeIBrisanjeAviona(AvionRepository avionRepo, LetRepository letRepo) {
		this.avionRepo = avionRepo;
		this.letRepo = letRepo;
	}
	
	@PostMapping("/dodavanjeAviona")
	public ResponseEntity<String> dodavanjeAvionaPost(@RequestBody Avion_Form avionForm) {
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
	
	@PostMapping("/brisanjeAviona")
	public ResponseEntity<String> brisanjeAvionaPost(@RequestBody Avion_Form avionForm) {
		try {
			
			long idAviona = avionForm.getIdAviona();
			Avion avion = avionRepo.findByIdAviona(idAviona);
			
			List<Let> letovi = letRepo.selectFlightWithPlane(avion);
			
			if(letovi.size() == 0) {
				avion.setCancled(true);
				avionRepo.save(avion);
				return new ResponseEntity<>("success", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Avion ne moze biti obrisan jer pripada letu.", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
}
