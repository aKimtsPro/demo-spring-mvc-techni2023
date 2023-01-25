package be.bstorm.akimts.mvc;

import be.bstorm.akimts.mvc.patterns.Personne;
import be.bstorm.akimts.mvc.utils.EMFSharer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoSpringMvcApplication {

	public static void main(String[] args) {
		ApplicationContext ctxt = SpringApplication.run(DemoSpringMvcApplication.class, args);

		ctxt.getBean(EMFSharer.class).close();
	}

}
