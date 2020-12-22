package app.listener;

import java.util.Random;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener (destination = "user.queue")
	public void consume(String email) {

		System.out.println("Starting Sending Email to: " + email);

		Random rand = new Random();

		for (int i = 0; i < rand.nextInt(10); i++) {
			try {
				Thread.sleep(1000);
				System.out.println("sending..");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Email successfully sent to: " + email);
	}
}
