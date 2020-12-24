package app.controller;

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
	public ResponseEntity<String> karticaPost(@RequestBody Let_Form letForm) {
		
		try {
			List<Let> let = letRepo.searchLetByParameters(letForm.getPocetnaDestinacija(), letForm.getKrajnjaDestinacija(),
					letForm.getCena(), letForm.getDuzinaLeta(), letForm.isCanceled());
			
			for (Let l : let) {
				System.out.println(l.getPocetnaDestinacija() + "\n");
			}
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
