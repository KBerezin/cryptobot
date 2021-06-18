package com.bootybanger.cryptobot.common.integration.config.properties.exchange;


import com.bootybanger.cryptobot.common.integration.factory.YamlPropertyLoaderFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("binance")
@PropertySource(value = "classpath:binance.yml", factory = YamlPropertyLoaderFactory.class)
public class BinanceExchangeConfigurationProperties extends AbstractGeneralExchangeConfigurationProperties {
}
