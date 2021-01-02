package app.controller;

import static app.security.SecurityConstants.ADMIN_SECRET;
import static app.security.SecurityConstants.ADMIN_TOKEN_PREFIX;
import static app.security.SecurityConstants.HEADER_STRING;

import java.util.ArrayList;
import java.util.List;

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
import app.entities.Let;
import app.forms.Avion_Form;
import app.forms.Let_Form;
import app.repository.AvionRepository;
import app.repository.LetRepository;

@RestController
@RequestMapping("/admin")
public class DodavanjeIBrisanjeLetovaController {

	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	Queue kartaQue;
	
	@Autowired
	Queue userQue;
	
	private LetRepository letRepo;
	
	private AvionRepository avionRepo;
	
	@Autowired
	public DodavanjeIBrisanjeLetovaController(LetRepository letRepo, AvionRepository avionRepo) {
		this.letRepo = letRepo;
		this.avionRepo = avionRepo;
	}
	
	@GetMapping("/{imeLeta}")
	public ResponseEntity<String> updateKarteProduct(@RequestHeader(value = HEADER_STRING) String token,
			@PathVariable String imeLeta) {

		try {
			String email = JWT.require(Algorithm.HMAC512(ADMIN_SECRET.getBytes())).build()
					.verify(token.replace(ADMIN_TOKEN_PREFIX, "")).getSubject();

			// stavljamo email u queue za email service
			//jmsTemplate.convertAndSend(userQue, email);

			return new ResponseEntity<String>("Flight successfully updated", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping("/dodajLet")
	public ResponseEntity<String> dodajLetProduct(@RequestBody Let_Form letForm) {

		try {
			Let let = new Let(letForm.getPocetnaDestinacija(), letForm.getKrajnjaDestinacija(), letForm.getDuzinaLeta(), letForm.getCena(), letForm.isCanceled());

			long idAviona = letForm.getIdAviona();

			//Trazimo avion u bazi podataka, da bi ga dodali na let
			Avion avion = avionRepo.findByIdAviona(idAviona);

			//Ako ne postoji avion sa prosledjenim id, vraca gresku
			if(avion == null) {
				return new ResponseEntity<String>("Ne postoji avion sa prosledjenim id-om!", HttpStatus.BAD_REQUEST);
			}else {
				//Taj avion dodajemo na let
				let.setAvion(avion);
			}

			letRepo.save(let);

			return new ResponseEntity<>("Let uspesno dodat!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping("/brisanjeLeta")
	public ResponseEntity<String> brisanjeLeta(@RequestBody Avion_Form avionForm){
		
		try {
			String imeAviona = avionForm.getNazivAviona();
			Let let = letRepo.selectFlightByPlaneName(imeAviona);
			if(let == null) {
				return new ResponseEntity<String> ("Let ne postoji.",HttpStatus.BAD_REQUEST);
			}
			
			long idLeta = let.getIdLeta();
			System.out.println("2134 idleta je "+ idLeta);
			int milje = let.getDuzinaLeta();
			
			List<Object> lista = new ArrayList<Object>();
			lista.add(idLeta);
			lista.add(milje);
			
			let.setCanceled(true);
			letRepo.save(let);
			
			//Saljemo poruku ka servisu za karte
			jmsTemplate.convertAndSend(kartaQue, lista);
			
			//Saljemo poruku ka servisu za usere
			jmsTemplate.convertAndSend(userQue, lista);
			
			return new ResponseEntity<String>("Let uspesno obrisan", HttpStatus.ACCEPTED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
