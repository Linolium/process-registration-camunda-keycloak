package com.example.processregistration;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main
 *
 * @author Voronenkov Aleksei
 * @since 15.02.2020
 */
@SpringBootApplication
@EnableProcessApplication
public class ProcessRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessRegistrationApplication.class, args);
    }

}
