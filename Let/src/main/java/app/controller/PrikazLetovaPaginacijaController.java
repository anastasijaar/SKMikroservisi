package app.controller;

import static app.security.SecurityConstants.HEADER_STRING;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Let;
import app.forms.Let_Form;
import app.repository.LetRepository;

@RestController
public class PrikazLetovaPaginacijaController {
	
	private LetRepository letRepo;
	
	public PrikazLetovaPaginacijaController(LetRepository letRepo) {
		this.letRepo = letRepo;
	}
	
	@PostMapping("/prikazLetova")
	public ResponseEntity<List<ResponseEntity<Let_Form>>> prikazLetovaPost(@RequestBody Let_Form letForm, @RequestHeader(value = HEADER_STRING) String token) {
		
		try {
			
			if(token.startsWith("Basic ")) {
				List<ResponseEntity<Let_Form>> responseEntiteti = new ArrayList<ResponseEntity<Let_Form>>();
				
				/*List<Let> let = letRepo.paginacijaByParameters(letForm.getCena(), letForm.getPocetnaDestinacija(), letForm.getKrajnjaDestinacija(), letForm.getDuzinaLeta(),
					 letForm.getIdAviona());
				
				List<Let> letovi = new ArrayList<Let>();
				
				for (Let l1 : let) {
					if(!l1.isCanceled()) {
						System.out.println("Ulazi u if");
						letovi.add(l1);
					}
					System.out.println("Let je: "+ l1);
				}*/
				
				Pageable onePageWithTwoElement = PageRequest.of(0, 3, Sort.by("duzinaLeta").descending());
				List<Let> let = letRepo.paginacijaByParameters(letForm.getCena(), letForm.getPocetnaDestinacija(), letForm.getKrajnjaDestinacija(), letForm.getDuzinaLeta(),
						 letForm.getIdAviona(), onePageWithTwoElement);
				System.out.println(let.size());
					
				if(let.size() == 0) {
					
					return new ResponseEntity<>(responseEntiteti, HttpStatus.BAD_REQUEST);
				}
				
				for (Let l : let) {
					responseEntiteti.add(new ResponseEntity<>(new Let_Form(l.getPocetnaDestinacija(), l.getKrajnjaDestinacija(),
							l.getCena(), l.getDuzinaLeta(), l.isCanceled()), HttpStatus.OK));
				}
				
				return new ResponseEntity<>(responseEntiteti, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	

}
