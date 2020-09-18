package com.example.processregistration.service;

import com.example.processregistration.model.RegistrationData;
import org.keycloak.representations.idm.UserRepresentation;

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
     * @param processInstanceId идентификатор процесса
     * @return идентификатор пользователя в keycloak
     */
    String createUser(RegistrationData data, String processInstanceId);

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

    /**
     * Существует ли уже пользователь с таким именем
     * @param username имя
     * @param email эмеил
     * @return пользователь
     */
    boolean isUserExists(String username, String email);
}
