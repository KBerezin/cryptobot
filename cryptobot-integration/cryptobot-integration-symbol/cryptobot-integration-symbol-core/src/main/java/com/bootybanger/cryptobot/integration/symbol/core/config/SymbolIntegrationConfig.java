package com.bootybanger.cryptobot.integration.symbol.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.bootybanger.cryptobot.common.integration.client"
})
public class SymbolIntegrationConfig {
    @Bean(name = "symbolObjectMapper")
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
