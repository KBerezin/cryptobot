package core.config;

import com.bootybanger.cryptobot.common.integration.config.IntegrationProperties;

import java.util.Map;

public interface SymbolProperties extends IntegrationProperties {
    Map<String, String> getSymbol();
}
