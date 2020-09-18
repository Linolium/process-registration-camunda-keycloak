package com.example.processregistration.service.impl;

import com.example.processregistration.model.RegistrationData;
import com.example.processregistration.service.RegistrationService;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Реализация сервиса регистрации
 *
 * @author Voronenkov Aleksei
 * @since 15.02.2020
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UsersResource usersResource;

    @Override
    public String createUser(RegistrationData data, String processInstanceId) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(false); // подтверждение выполняется в ручную
        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setAttributes(Collections.singletonMap("processInstanceId", Collections.singletonList(processInstanceId)));

        Response response = usersResource.create(user);

        // достаем userId
        String userId = response.getLocation().getPath()
                .replaceAll(".*/([^/]+)$", "$1");

        // задаем пользователю пароль
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(data.getPassword());

        usersResource.get(userId).resetPassword(passwordCred);
        return userId;
    }

    @Override
    public void enableUser(String userId) {
        UserResource userResource = usersResource.get(userId);
        UserRepresentation representation = userResource.toRepresentation();
        representation.setAttributes(new HashMap<>());
        representation.setEnabled(true);

        userResource.update(representation);
    }

    @Override
    public void addCancelNotice(String userId) {
        UserResource userResource = usersResource.get(userId);
        UserRepresentation representation = userResource.toRepresentation();
        representation.setAttributes(Collections.singletonMap("comments", Collections.singletonList("declined")));

        userResource.update(representation);
    }

    @Override
    public boolean isUserExists(String username, String email) {
        List<UserRepresentation> users = usersResource.list();
        UserRepresentation user = users.stream()
                .filter(u -> username.equals(u.getUsername()) || email.equals(u.getEmail()))
                .findAny()
                .orElse(null);
        return user != null;
    }
}
