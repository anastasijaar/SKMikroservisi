package app.controller;

import static app.security.SecurityConstants.HEADER_STRING;
import static app.security.SecurityConstants.ADMIN_SECRET;
import static app.security.SecurityConstants.ADMIN_TOKEN_PREFIX;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

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
	public ResponseEntity<List<ResponseEntity<Let_Form>>> karticaPost(@RequestBody Let_Form letForm, @RequestHeader(value = HEADER_STRING) String token) {
		
		try {
			
			if(token.startsWith("Basic ")) {
				List<ResponseEntity<Let_Form>> responseEntiteti = new ArrayList<ResponseEntity<Let_Form>>();
				
				List<Let> let = letRepo.searchLetByParameters(letForm.getPocetnaDestinacija(), letForm.getKrajnjaDestinacija(),
						letForm.getCena(), letForm.getDuzinaLeta(), letForm.isCanceled());
					
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
