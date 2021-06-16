package com.bootybanger.cryptobot.integration.asset.core.service.client;

import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import com.bootybanger.cryptobot.common.integration.config.properties.AssetProperties;
import com.bootybanger.cryptobot.integration.asset.core.domain.service.AbstractExchangeAssetClient;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AssetParseUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GateAssetClient extends AbstractExchangeAssetClient {
    public GateAssetClient(@Qualifier("gateAssetParseUtil") AssetParseUtil parseUtil,
                           @Qualifier("gateConfigurationProperties") AssetProperties properties,
                           @Qualifier("gateBaseClient") BaseClient client) {
        super(parseUtil, properties, client);
    }
}
