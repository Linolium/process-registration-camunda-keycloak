package com.example.processregistration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Проперти для кейлока
 *
 * @author Voronenkov Aleksei
 * @since 15.02.2020
 */
@Configuration
@ConfigurationProperties(value = "keycloak")
@Data
public class KeycloakProperties {

    private String serverUrl;

    private String realm;

    private String username;

    private String password;

    private String clientId;
}
