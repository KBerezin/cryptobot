package com.bootybanger.cryptobot.microservice.cache.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.bootybanger.cryptobot.cache.asset.api.controller",
        "com.bootybanger.cryptobot.cache.asset.core",
})
public class CacheMicroserviceApplicationConfig {
}
