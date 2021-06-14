package com.bootybanger.cryptobot.integration.core.domain.config;

import com.bootybanger.cryptobot.common.integration.config.IntegrationProperties;

import java.util.Map;

public interface AssetProperties extends IntegrationProperties {
    Map<String, String> getAsset();
}
