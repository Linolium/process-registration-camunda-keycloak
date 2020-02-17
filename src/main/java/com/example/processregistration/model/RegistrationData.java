package com.example.processregistration.model;

import lombok.Data;

/**
 * Модель данных для регистрации
 *
 * @author Voronenkov Aleksei
 * @since 15.02.2020
 */
@Data
public class RegistrationData {
    private String username;
    private String password;
    private String email;
}
