package be.bstorm.akimts.mvc;

import be.bstorm.akimts.mvc.models.entity.Hotel;
import be.bstorm.akimts.mvc.models.entity.Reservation;
import be.bstorm.akimts.mvc.patterns.Personne;
import be.bstorm.akimts.mvc.repository.ReservationRepository;
import be.bstorm.akimts.mvc.utils.EMFSharer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@SpringBootApplication
public class DemoSpringMvcApplication {

	public static void main(String[] args) {
		ApplicationContext ctxt = SpringApplication.run(DemoSpringMvcApplication.class, args);

		ReservationRepository repository = ctxt.getBean(ReservationRepository.class);

		System.out.println("-- FOR MONTH --");
		repository.getFromMonth(Month.OCTOBER, 2023).forEach(System.out::println);

		System.out.println("-- BREAKFAST --");
		long breakfasts = repository.getBreakfastNeededForDay(LocalDate.of(2023, 10,3));
		System.out.println( breakfasts );

		System.out.println("-- FUTURE --");
		repository.getFutureReservation().forEach(System.out::println);
//
//		System.out.println("-- FUTURE SHORT --");
//		repository.getFutureShortReservation().forEach(System.out::println);
////
//		System.out.println("-- FUTURE LONG --");
//		repository.getFutureLongReservation().forEach(System.out::println);

		ctxt.getBean(EMFSharer.class).close();
	}

}
