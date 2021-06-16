package com.bootybanger.cryptobot.common.integration.config.properties;

import java.util.Map;

public interface SymbolProperties extends IntegrationProperties {
    Map<String, String> getSymbol();
}
