package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KartaApplication {
	
	@Bean
	public static BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder(10);
	}
=======

@SpringBootApplication
public class KartaApplication {
>>>>>>> branch 'main' of https://github.com/anastasijaar/SKMikroservisi.git

	public static void main(String[] args) {
		SpringApplication.run(KartaApplication.class, args);
	}

}
