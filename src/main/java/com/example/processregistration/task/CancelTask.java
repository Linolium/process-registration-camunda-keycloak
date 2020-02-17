package com.example.processregistration.task;

import com.example.processregistration.service.RegistrationService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Задача отказа в регистрации
 *
 * @author Voronenkov Aleksei
 * @since 15.02.2020
 */
@Component
public class CancelTask implements JavaDelegate {

    private Logger logger = LoggerFactory.getLogger(CancelTask.class);

    @Autowired
    private RegistrationService registrationService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String userId = (String) execution.getVariable("userId");
        registrationService.addCancelNotice(userId);
        logger.debug("===== END PROCESS (Cancel) =====");
    }
}
