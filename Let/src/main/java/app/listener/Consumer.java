package app.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import app.entities.Let;
import app.repository.LetRepository;


@Component
public class Consumer {
	
	/*private LetRepository letRepo;
	
	@Autowired
	public Consumer(LetRepository letRepo) {
		this.letRepo = letRepo;
	}
	
	@JmsListener (destination = "let.queue")
	public void consume(int id) {
		
		Let let = letRepo.findByIdLeta(id);
		let.setCanceled(true);
		letRepo.save(let);
		
	}*/
}
