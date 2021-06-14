package com.bootybanger.cryptobot.integration.core.config.properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SymbolIntegrationConfig {
    @Bean(name = "symbolObjectMapper")
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
