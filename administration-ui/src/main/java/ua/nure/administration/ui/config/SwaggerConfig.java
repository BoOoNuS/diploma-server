package ua.nure.administration.ui.config;

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
                .apis(RequestHandlerSelectors.basePackage("ua.nure.administration.ui"))
                .paths(any()).build().apiInfo(new ApiInfo("Administration with UI service, similar to gateway",
                        "A set of services to provide data access to all micro services", "0.0.1-SNAPSHOT", null,
                        new Contact("Stanislav Vorozhka", null, "stanislav.vorozhka@nure.ua"), null, null));
    }
}
