package ua.nure.heating.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Entry point for the application.
 *
 * @author Stanislav_Vorozhka
 */
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class HeatingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeatingServiceApplication.class, args);
    }
}

