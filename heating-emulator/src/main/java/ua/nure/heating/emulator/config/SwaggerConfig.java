package ua.nure.heating.emulator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.any;

/**
 * Swagger configuration.
 *
 * @author Stanislav_Vorozhka
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Administration UI").select()
                .apis(RequestHandlerSelectors.basePackage("ua.nure.heating.emulator"))
                .paths(any()).build().apiInfo(new ApiInfo("Heating emulator",
                        "Heating emulator service, provide users with access to the emulator", "0.0.1-SNAPSHOT", null,
                        new Contact("Stanislav Vorozhka", null, "stanislav.vorozhka@nure.ua"), null, null));
    }
}
