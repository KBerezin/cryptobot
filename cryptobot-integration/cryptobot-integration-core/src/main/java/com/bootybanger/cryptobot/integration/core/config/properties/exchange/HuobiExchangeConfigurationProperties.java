package com.bootybanger.cryptobot.integration.core.config.properties.exchange;

import com.bootybanger.cryptobot.integration.core.domain.config.properties.exchange.AbstractGeneralExchangeConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.factory.YamlPropertyLoaderFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("huobi")
@PropertySource(value = "classpath:huobi.yml", factory = YamlPropertyLoaderFactory.class)
public class HuobiExchangeConfigurationProperties extends AbstractGeneralExchangeConfigurationProperties {
}
