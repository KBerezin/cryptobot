package com.bootybanger.cryptobot.microservice.catalog.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.bootybanger.cryptobot.catalog.api.controller",
        "com.bootybanger.cryptobot.catalog.core"
})
@EntityScan(basePackages = "com.bootybanger.cryptobot.catalog.core.entity")
@EnableJpaRepositories(basePackages = "com.bootybanger.cryptobot.catalog.core.repository")
public class CatalogMicroserviceApplicationConfig {
}

