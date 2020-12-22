package app.controller;

import static app.security.SecurityConstants.HEADER_STRING;
import static app.security.SecurityConstants.SECRET;
import static app.security.SecurityConstants.ADMIN_SECRET;
import static app.security.SecurityConstants.ADMIN_TOKEN_PREFIX;
import static app.security.SecurityConstants.TOKEN_PREFIX;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import app.entities.Avion;
import app.forms.Avion_Form;
import app.repository.AvionRepository;

@RestController
@RequestMapping("/admin")
public class DodavanjeIBrisanjeAviona {

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	Queue userQue;
	
	private AvionRepository avionRepo;
	
	@Autowired
	public DodavanjeIBrisanjeAviona (AvionRepository avionRepo) {
		this.avionRepo = avionRepo;
	}

	@GetMapping("/{imeLeta}")
	public ResponseEntity<String> updateKarteProduct(@RequestHeader(value = HEADER_STRING) String token,
			@PathVariable String imeLeta) {

		try {
			String email = JWT.require(Algorithm.HMAC512(ADMIN_SECRET.getBytes())).build()
					.verify(token.replace(ADMIN_TOKEN_PREFIX, "")).getSubject();

			// stavljamo email u queue za email service
			jmsTemplate.convertAndSend(userQue, email);

			return new ResponseEntity<String>("Flight successfully updated", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping("/dodajLet")
	public ResponseEntity<String> dodajAvionProduct(@RequestBody Avion_Form avionForm) {

		try {
			Avion avion = new Avion(avionForm.getNazivAviona(), avionForm.getKapacitet(), avionForm.getTrenutnoPutnika());
			
			avionRepo.save(avion);

			// stavljamo email u queue za email service
			jmsTemplate.convertAndSend(userQue, avion);

			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}
}
