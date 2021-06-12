package com.bootybanger.cryptobot.microservice.exchange.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class ExchangeIntegrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeIntegrationApplication.class, args);
    }
}


