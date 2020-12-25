package app.listener;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import app.entities.TipRanka;
import app.entities.User;
import app.repository.UserRepository;

@Component
public class Consumer {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	public Consumer(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@JmsListener (destination = "user.queue")
	public void consume(List<Object> lista){
		
		//Nadjem sve usere koji idu na prosledjeni let
		long idLeta = (long) lista.get(0);
		
		List<User> useri = userRepo.findAllUsersThatGoOnFlight(idLeta);
		
		int miljeZaOduzeti = (int) lista.get(1);
		
		/*for (User user : useri) {
			sendMail(user.getEmail(), user.getIme(), user.getRank(), user.getPredjeneMilje(), miljeZaOduzeti);
		}*/
	}
	
	public static void sendMail(String to, String ime, TipRanka rank, int milje, int miljeZaOduzeti) throws Exception {
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
		
		Message message = prepareMessage(session, myAccountEmail, to, ime, rank, milje, miljeZaOduzeti);
		
		Transport.send(message);
	}
	
	//Pravimo poruku
	private static Message prepareMessage(Session session, String myAccountEmail, String to, String ime, TipRanka rank, int milje, int miljeZaOduzeti) {
		try {
			int oduzeteMilje = milje - miljeZaOduzeti;
			
			TipRanka noviRank;
			if(milje < 1000) {
				noviRank = TipRanka.BRONZA;
			}
			else if(milje >= 1000 && milje < 10000) {
				noviRank = TipRanka.SREBRO;
			}
			else
			{
				noviRank = TipRanka.ZLATO;
			}
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Povracaj novca!");
			message.setText("Postovani/a " + ime + ",\n\nObavestavamo Vas da je uradjen povracaj novca zbog otkazivanja leta.\n"
										+ "Predjene milje i rank pre otkazivanja leta:\n " + "Milje: " + milje + "\nRank: " + rank 
										+ "\\n\nPredjene milje i rank nakon otkazivanja leta:\n " + "Milje: " + oduzeteMilje + "\nRank: " + noviRank);
			System.out.println("Poslao poruku");
			return message;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
