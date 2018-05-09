package ua.nure.heating.emulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Entry point for the application.
 *
 * @author Stanislav_Vorozhka
 */
@EnableSwagger2
@SpringBootApplication
public class HeatingEmulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeatingEmulatorApplication.class, args);
	}
}
