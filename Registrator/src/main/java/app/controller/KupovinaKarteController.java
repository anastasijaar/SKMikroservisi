package app.controller;

import static app.security.SecurityConstants.HEADER_STRING;
import static app.security.SecurityConstants.SECRET;
import static app.security.SecurityConstants.TOKEN_PREFIX;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import app.entities.User;
import app.entities.User_Kartica;
import app.forms.KupovinaKarte_Form;
import app.repository.UserRepository;


@RestController
public class KupovinaKarteController {
	
	private UserRepository userRepo;
	
	public KupovinaKarteController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping("/kupovinaKarte")
	public ResponseEntity<List<Long>> karticaGet(@RequestHeader(value = HEADER_STRING) String token) {
		try {
			System.out.println("Udje ovdje");
			
			// izvlacimo iz tokena subject koj je postavljen da bude email
			String email = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getSubject();

			User user = userRepo.findByEmail(email);
			List<Long> idKartice = userRepo.selectAllKarticeForUser(user);
			
			if(idKartice.size() == 0) {
				
				return new ResponseEntity<List<Long>>(HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(idKartice, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
