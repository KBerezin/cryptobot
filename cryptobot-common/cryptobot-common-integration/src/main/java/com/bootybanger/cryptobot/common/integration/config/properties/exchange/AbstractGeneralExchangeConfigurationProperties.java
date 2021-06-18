package com.bootybanger.cryptobot.common.integration.config.properties.exchange;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter @Getter
public abstract class AbstractGeneralExchangeConfigurationProperties implements GeneralExchangeProperties {
    @NotBlank
    private String baseUrl;
    @NotEmpty
    private Map<String, String> symbol;
    @NotEmpty
    private Map<String, String> asset;
}
