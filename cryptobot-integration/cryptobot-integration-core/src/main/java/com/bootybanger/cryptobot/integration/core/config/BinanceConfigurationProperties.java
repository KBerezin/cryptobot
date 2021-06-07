package com.bootybanger.cryptobot.integration.core.config;

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
@ConfigurationProperties("binance")
@PropertySource(value = "classpath:binance.yml", factory = YamlPropertyLoaderFactory.class)
@Setter @Getter
public class BinanceConfigurationProperties {
    @NotBlank
    private String baseUrl;
    @NotEmpty
    private Map<String, String> symbol;
    @NotEmpty
    private Map<String, String> asset;
}
