package com.bootybanger.cryptobot.common.integration.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonIntegrationConfig {
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
