package app.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import app.entities.Karta;
import app.repository.KartaRepository;

@Component
public class Consumer {

	
	private KartaRepository kartaRepo;
	
	@Autowired
	public Consumer(KartaRepository kartaRepo) {
		this.kartaRepo = kartaRepo;
	}
	
	@JmsListener (destination = "let.queue")
	public void consume(int id) {
		System.out.println("Udje u karta klasu!");
		Karta karta = kartaRepo.selectCardByPlaneID(id);
		
		if(karta == null) {
			return;
		}
		
		karta.setCanceled(true);
		kartaRepo.save(karta);
	}
}
