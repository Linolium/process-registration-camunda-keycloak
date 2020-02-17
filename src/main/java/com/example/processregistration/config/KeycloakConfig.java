package com.example.processregistration.config;

import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация кейклока
 *
 * @author Voronenkov Aleksei
 * @since 15.02.2020
 */
@Configuration
public class KeycloakConfig {

    @Autowired
    private KeycloakProperties properties;

    @Bean
    public UsersResource getKeycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(properties.getServerUrl())
                .realm(properties.getRealm())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .clientId(properties.getClientId())
                .grantType("password")
                .build()
                .realm(properties.getRealm())
                .users();
    }
}
