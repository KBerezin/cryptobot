package com.bootybanger.cryptobot.integration.core.config.properties.exchange;

import com.bootybanger.cryptobot.integration.core.domain.config.properties.exchange.AbstractGeneralExchangeConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.factory.YamlPropertyLoaderFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@ConfigurationProperties("gate")
@PropertySource(value = "classpath:gate.yml", factory = YamlPropertyLoaderFactory.class)
public class GateConfigurationProperties extends AbstractGeneralExchangeConfigurationProperties {
}
