package com.bootybanger.cryptobot.common.integration.config.properties;

import java.util.Map;

public interface AssetProperties extends IntegrationProperties {
    Map<String, String> getAsset();
}
