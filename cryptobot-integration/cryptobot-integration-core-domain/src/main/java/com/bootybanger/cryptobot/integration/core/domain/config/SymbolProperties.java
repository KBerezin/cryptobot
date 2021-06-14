package com.bootybanger.cryptobot.integration.core.domain.config;

import com.bootybanger.cryptobot.common.integration.config.IntegrationProperties;

import java.util.Map;

public interface SymbolProperties extends IntegrationProperties {
    Map<String, String> getSymbol();
}
