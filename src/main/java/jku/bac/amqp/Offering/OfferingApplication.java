package jku.bac.amqp.Offering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class OfferingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfferingApplication.class, args);

	}

}
