package com.example.processregistration.task;

import com.example.processregistration.model.RegistrationData;
import com.example.processregistration.service.RegistrationService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.camunda.spin.Spin.JSON;

/**
 * Задача регистрации пользователя
 *
 * @author Voronenkov Aleksei
 * @since 15.02.2020
 */
@Component
public class RegistrationTask implements JavaDelegate {

    private Logger logger = LoggerFactory.getLogger(RegistrationTask.class);

    @Autowired
    private RegistrationService registrationService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.debug("===== START PROCESS =====");
        // в данном примере достаем входные данные из запроса запуска процесса
        String json = (String) execution.getVariable("json");
        RegistrationData data = JSON(json).mapTo(RegistrationData.class);

        if (data.getUsername() == null || data.getPassword() == null
                || data.getEmail() == null) {
            logger.error("====== NO_DATA =====");
            throw new BpmnError("NO_DATA");
        }

        if (registrationService.isUserExists(data.getUsername(), data.getEmail())) {
            logger.error("====== USERNAME OR EMAIL ALREADY EXISTS =====");
            throw new BpmnError("USER_OR_EMAIL_ALREADY_EXISTS");
        }

        String userId = registrationService.createUser(data, execution.getProcessInstanceId());
        execution.setVariable("userId", userId);
        execution.setVariable("username", data.getUsername());
        execution.setVariable("email", data.getEmail());
    }

}
