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
@ConfigurationProperties("kucoin")
@PropertySource(value = "classpath:kucoin.yml", factory = YamlPropertyLoaderFactory.class)
@Setter @Getter
public class KuCoinConfigurationProperties {
    @NotBlank
    private String baseUrl;
    @NotEmpty
    private Map<String, String> symbol;
}
