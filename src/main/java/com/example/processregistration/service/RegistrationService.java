package com.example.processregistration.service;

import com.example.processregistration.model.RegistrationData;

/**
 * Сервис регистрации
 *
 * @author Voronenkov Aleksei
 * @since 15.02.2020
 */
public interface RegistrationService {

    /**
     * Создание пользователя
     * @param data данные для регистрации
     * @return идентификатор пользователя в keycloak
     */
    String createUser(RegistrationData data);

    /**
     * Включение пользователя
     * @param userId идентификатор пользователя
     */
    void enableUser(String userId);

    /**
     * Добавление сообщения об отказе
     * @param userId идентификатор пользователя
     */
    void addCancelNotice(String userId);
}
