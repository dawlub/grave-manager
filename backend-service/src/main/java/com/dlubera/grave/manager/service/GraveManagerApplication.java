package com.dlubera.grave.manager.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.SecurityMarker;

@SpringBootApplication
@EnableConfigurationProperties
public class GraveManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraveManagerApplication.class, args);
    }

}
