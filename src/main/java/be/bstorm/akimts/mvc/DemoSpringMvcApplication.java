package be.bstorm.akimts.mvc;

import be.bstorm.akimts.mvc.patterns.Personne;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringMvcApplication.class, args);


		Personne p = Personne.builder("luc")
				.nom("dubois")
				.build();
	}

}
