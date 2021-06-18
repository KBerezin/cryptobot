package com.bootybanger.cryptobot.common.integration.config.properties.exchange;

import com.bootybanger.cryptobot.common.integration.config.properties.IntegrationProperties;

import java.util.Map;

public interface SymbolProperties extends IntegrationProperties {
    Map<String, String> getSymbol();
}
