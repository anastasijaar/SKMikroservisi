package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Let;
import app.forms.Let_Form;
import app.repository.LetRepository;

@RestController
public class PretrazivanjeLetovaController {
	
	private LetRepository letRepo;
	
	public PretrazivanjeLetovaController(LetRepository letRepo) {
		this.letRepo = letRepo;
	}

	
	@PostMapping("/pretrazivanjeLetova")
	public ResponseEntity<List<ResponseEntity<Let_Form>>> karticaPost(@RequestBody Let_Form letForm) {
		
		try {
			List<Let> let = letRepo.searchLetByParameters(letForm.getPocetnaDestinacija(), letForm.getKrajnjaDestinacija(),
					letForm.getCena(), letForm.getDuzinaLeta(), letForm.isCanceled());
			
			List<ResponseEntity<Let_Form>> responseEntiteti = new ArrayList<ResponseEntity<Let_Form>>();
			
			for (Let l : let) {
				responseEntiteti.add(new ResponseEntity<>(new Let_Form(l.getPocetnaDestinacija(), l.getKrajnjaDestinacija(),
						l.getCena(), l.getDuzinaLeta(), l.isCanceled()), HttpStatus.OK));
			}
			
			return new ResponseEntity<>(responseEntiteti, HttpStatus.OK);
			//return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
