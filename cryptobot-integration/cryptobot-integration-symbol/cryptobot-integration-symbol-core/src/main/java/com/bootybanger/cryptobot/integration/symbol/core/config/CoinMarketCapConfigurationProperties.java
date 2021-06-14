package com.bootybanger.cryptobot.integration.symbol.core.config;

import com.bootybanger.cryptobot.common.integration.factory.YamlPropertyLoaderFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Configuration
@ConfigurationProperties("coinmarketcap")
@PropertySource(value = "classpath:coinmarketcap.yml", factory = YamlPropertyLoaderFactory.class)
@Setter
@Getter
public class CoinMarketCapConfigurationProperties {
    @NotBlank
    private String baseUrl;
    @NotEmpty
    private Map<String, String> coin;
    @NotEmpty
    private Map<String, String> headers;
}
