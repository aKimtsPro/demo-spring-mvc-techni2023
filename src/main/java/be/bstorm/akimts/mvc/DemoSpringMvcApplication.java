package be.bstorm.akimts.mvc;

import be.bstorm.akimts.mvc.models.entity.Hotel;
import be.bstorm.akimts.mvc.patterns.Personne;
import be.bstorm.akimts.mvc.repository.ReservationRepository;
import be.bstorm.akimts.mvc.utils.EMFSharer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.Month;

@SpringBootApplication
public class DemoSpringMvcApplication {

	public static void main(String[] args) {
		ApplicationContext ctxt = SpringApplication.run(DemoSpringMvcApplication.class, args);

		ReservationRepository repository = ctxt.getBean(ReservationRepository.class);
		repository.getFromMonth(Month.OCTOBER, 2023).forEach(System.out::println);

		ctxt.getBean(EMFSharer.class).close();
	}

}
