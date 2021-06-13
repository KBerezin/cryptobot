package com.bootybanger.cryptobot.integration.core.domain.config.properties.exchange;

import com.bootybanger.cryptobot.integration.core.domain.config.properties.IntegrationProperties;

import java.util.Map;

public interface AssetProperties extends IntegrationProperties {
    Map<String, String> getAsset();
}
