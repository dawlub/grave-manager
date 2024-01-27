package com.dlubera.grave.manager.service.authentication.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("jwt.permit")
class SecurityPermissionProperties {

    private List<String> all;
}
