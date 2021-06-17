package com.bootybanger.cryptobot.common.integration.config.properties;

import com.bootybanger.cryptobot.common.integration.factory.YamlPropertyLoaderFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("bitfinex")
@PropertySource(value = "classpath:bitfinex.yml", factory = YamlPropertyLoaderFactory.class)
public class BitfinexIntegrationProperties extends AbstractGeneralExchangeConfigurationProperties{
}
