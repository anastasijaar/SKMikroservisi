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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import app.entities.Kartica;
import app.entities.TipRanka;
import app.entities.User;
import app.entities.User_Kartica;
import app.forms.Kartica_Form;
import app.forms.Rank_Form;
import app.forms.RegistrationForm;
import app.forms.UrediProfil_Form;
import app.repository.KarticaRepository;
import app.repository.UserRepository;
import app.repository.User_KarticaRepository;

//importi za mejl
import javax.mail.*;
import javax.mail.internet.*;


@RestController
@RequestMapping("")
public class Controller {

	private BCryptPasswordEncoder encoder;
	private UserRepository userRepo;
	private KarticaRepository karticaRepo;
	private User_KarticaRepository userKarticaRepo;

	@Autowired
	public Controller(BCryptPasswordEncoder encoder, UserRepository userRepo, KarticaRepository karticaRepo, User_KarticaRepository userKarticaRepo) {
		this.encoder = encoder;
		this.userRepo = userRepo;
		this.karticaRepo = karticaRepo;
		this.userKarticaRepo = userKarticaRepo;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerPost(@RequestBody RegistrationForm registrationForm) {

		try {
			
			//Proveravam da li u bazi postoji korisnik sa ovakvim mejlom, i ako postoji vracam BAD_REQUEST
			User proveraMejla = userRepo.findByEmail(registrationForm.getEmail());
			if(proveraMejla != null) {
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
	
	@PutMapping("/urediProfil")
	public ResponseEntity<String> registerPut(@RequestHeader(value = HEADER_STRING) String token, @RequestBody UrediProfil_Form urediProfilForm){
		try {
			
			String email = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getSubject();

			User user = userRepo.findByEmail(email);
			
			String mail = user.getEmail();
			String ime = user.getIme();
			String prezime = user.getPrezime();
			String brPasosa = user.getBrojPasosa();
			String password = user.getPassword();
			
			System.out.println("Stari podaci:\nmail: " + mail + "\nime:" + ime + "\nprezime: " + prezime +"\nbrPasosa: " + brPasosa + "\npassword: " + password);
			
			
			//Setujemo stare vrednosti na nove
			if(urediProfilForm.getIme() != null)
				user.setIme(urediProfilForm.getIme());
			else
				user.setIme(ime);
			
			if(urediProfilForm.getPrezime() != null)
				user.setPrezime(urediProfilForm.getPrezime());
			else 
				user.setPrezime(prezime);
			
			if(urediProfilForm.getBrojPasosa() != null)
				user.setBrojPasosa(urediProfilForm.getBrojPasosa());
			else 
				user.setBrojPasosa(brPasosa);

			if(urediProfilForm.getEmail() != null) {
				user.setEmail(urediProfilForm.getEmail());
			}
			else 
				user.setEmail(mail);
			
			if(mail != user.getEmail()) {
				sendMail(user.getEmail());
			}
			
			if(urediProfilForm.getPassword() != null)
				user.setPassword(encoder.encode(urediProfilForm.getPassword()));
			else
				user.setPassword(password);
			
			userRepo.save(user);
			
			
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/dodelaKreditneKartice")
	public ResponseEntity<String> karticaPost(@RequestBody Kartica_Form karticaForm, @RequestHeader(value = HEADER_STRING) String token) {
		try {
			
			String email = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getSubject();

			User user = userRepo.findByEmail(email);
			
			Kartica kartica = new Kartica(karticaForm.getBrojKartice(), karticaForm.getSigurnosniBroj());
			kartica.setImeVlasnika(user.getIme());
			kartica.setPrezimeVlasnika(user.getPrezime());
			
			karticaRepo.save(kartica);
			
			User_Kartica userKartica = new User_Kartica();
			userKartica.setKartica(kartica);
			userKartica.setUser(user);
			
			userKarticaRepo.save(userKartica);
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/dodavanjeMilja")
	public ResponseEntity<String> dodavanjeMiljaPut(@RequestBody Rank_Form rankForm, @RequestHeader(value = HEADER_STRING) String token){
		
		try {
			
			String email = JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
					.verify(token.replace(TOKEN_PREFIX, "")).getSubject();

			User user = userRepo.findByEmail(email);
			
			int milje = rankForm.getMilje();
			
			user.setPredjeneMilje(user.getPredjeneMilje() + milje);
			
			if(user.getPredjeneMilje() < 1000) {
				user.setRank(TipRanka.BRONZA);
			}
			else if(user.getPredjeneMilje() >= 1000 && user.getPredjeneMilje() < 10000) {
				user.setRank(TipRanka.SREBRO);
			}
			else
			{
				user.setRank(TipRanka.ZLATO);
			}
			
			userRepo.save(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	

	
}