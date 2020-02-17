package com.example.processregistration.task;

import com.example.processregistration.service.RegistrationService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Задача активации пользователя
 *
 * @author Voronenkov Aleksei
 * @since 15.02.2020
 */
@Component
public class EnableTask implements JavaDelegate {

    private Logger logger = LoggerFactory.getLogger(EnableTask.class);

    @Autowired
    private RegistrationService registrationService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String userId = (String) execution.getVariable("userId");
        registrationService.enableUser(userId);
        logger.debug("===== END PROCESS (Enable) =====");
    }
}
