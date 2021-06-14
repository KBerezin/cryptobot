package com.bootybanger.cryptobot.integration.symbol.core.config;


import com.bootybanger.cryptobot.common.integration.factory.YamlPropertyLoaderFactory;
import core.config.AbstractGeneralExchangeConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("gate")
@PropertySource(value = "classpath:gate.yml", factory = YamlPropertyLoaderFactory.class)
public class GateConfigurationProperties extends AbstractGeneralExchangeConfigurationProperties {
}
