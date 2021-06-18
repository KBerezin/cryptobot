package com.bootybanger.cryptobot.common.integration.config.properties.internal;

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
@ConfigurationProperties("cache")
@PropertySource(value = "classpath:internal.yml", factory = YamlPropertyLoaderFactory.class)
@Setter @Getter
public class CacheConfigurationProperties {
    @NotBlank
    private String baseUrl;
    @NotEmpty
    private Map<String, String> assetCache;
    @NotEmpty
    private Map<String, String> arbitrageWindowCache;
}