package com.bootybanger.cryptobot.microservice.asset.monitoring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.bootybanger.cryptobot.asset.monitoring.api.controller",
        "com.bootybanger.cryptobot.asset.monitoring.core"
})
public class AssetMonitoringApplicationConfig {
}
