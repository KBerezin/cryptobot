package com.bootybanger.cryptobot.integration.core.config.properties;

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
@ConfigurationProperties("catalog")
@PropertySource(value = "classpath:catalog.yml", factory = YamlPropertyLoaderFactory.class)
@Setter @Getter
public class CatalogConfigurationProperties {
    @NotBlank
    private String baseUrl;
    @NotEmpty
    private Map<String, String> symbol;
    @NotEmpty
    private Map<String, String> coin;
}
