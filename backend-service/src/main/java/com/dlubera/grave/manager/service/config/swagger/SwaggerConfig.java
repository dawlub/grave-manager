package com.dlubera.grave.manager.service.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

@Configuration
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SwaggerConfig {

    private static final String SERVICE_NAME = "Grave Manager Backend Service";
    private static final String DESCRIPTION = """
            Grave manager backend service provides all required logic and set of endpoints to manage 
            cemetery, graves and visits.
            API have grouped modules each module have separate detailed description and set of endpoints.
            """;

    private static final String PROJECT_VERSION = "1.0";

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info().title(SERVICE_NAME)
                        .description(DESCRIPTION)
                        .version(PROJECT_VERSION)
        );
    }
}
