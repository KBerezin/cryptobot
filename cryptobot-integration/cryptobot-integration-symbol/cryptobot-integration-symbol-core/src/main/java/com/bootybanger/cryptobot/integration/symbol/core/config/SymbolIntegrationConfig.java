package com.bootybanger.cryptobot.integration.symbol.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.bootybanger.cryptobot.common.integration.client"
})
public class SymbolIntegrationConfig {
}
