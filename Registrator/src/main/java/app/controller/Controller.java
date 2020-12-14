package app.controller;

import static app.security.SecurityConstants.HEADER_STRING;
import static app.security.SecurityConstants.SECRET;
import static app.security.SecurityConstants.TOKEN_PREFIX;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import app.entities.User;
import app.forms.RegistrationForm;
import app.repository.UserRepository;

//importi za mejl
import javax.mail.*;
import javax.mail.internet.*;


@RestController
@RequestMapping("")
public class Controller {

	private BCryptPasswordEncoder encoder;
	private UserRepository userRepo;

	@Autowired
	public Controller(BCryptPasswordEncoder encoder, UserRepository userRepo) {
		this.encoder = encoder;
		this.userRepo = userRepo;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerPost(@RequestBody RegistrationForm registrationForm) {

		try {
			
			//Proveravam da li u bazi postoji korisnik sa ovakvim mejlom, i ako postoji vracam BAD_REQUEST
			User proveraMeila = userRepo.findByEmail(registrationForm.getEmail());
			if(proveraMeila != null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			// iscitavamo entitet iz registracione forme
			User user = new User(registrationForm.getIme(), registrationForm.getPrezime(), registrationForm.getEmail(),
					encoder.encode(registrationForm.getPassword()),registrationForm.getPredjeneMilje(), registrationForm.getBrojPasosa());

			// cuvamo u nasoj bazi ovaj entitet
			userRepo.saveAndFlush(user);

			
			//SLANJE email-a !!!!!!!
			sendMail(registrationForm.getEmail());
			
			
			
			//Sve je proslo uspesno i saljemo status 200(OK) i ispisujemo poruku 'success'
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	public static void sendMail(String to) throws Exception {
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", 587);
		
		String myAccountEmail = "flywithraf@gmail.com";
		String password = "anastasijaIluka15";
		
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override 
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		
		Message message = prepareMessage(session, myAccountEmail, to);
		
		Transport.send(message);
	}
	
	//Pravimo poruku
	private static Message prepareMessage(Session session, String myAccountEmail, String to) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Registracija!");
			message.setText("Cestitamo,\n\nuspesno ste se registrovali.\n\nSrdacan pozdrav, Vas FlyWithRAF!");
			return message;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/whoAmI")
	public ResponseEntity<String> whoAmI(@RequestHeader(value = HEADER_STRING) String token) {
		try {

			// izvlacimo iz tokena subject koj je postavljen da bude email
			String email = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getSubject();

			User user = userRepo.findByEmail(email);

			return new ResponseEntity<>(user.getIme() + " " + user.getPrezime(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}