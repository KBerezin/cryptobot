package com.bootybanger.cryptobot.integration.asset.core.service.client;

import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import com.bootybanger.cryptobot.common.integration.config.properties.exchange.AssetProperties;
import com.bootybanger.cryptobot.integration.asset.core.domain.service.AbstractExchangeAssetClient;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AssetParseUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BitfinexAssetClient extends AbstractExchangeAssetClient {
    public BitfinexAssetClient(@Qualifier("bitfinexAssetParseUtil") AssetParseUtil parseUtil,
                               @Qualifier("bitfinexExchangeIntegrationProperties") AssetProperties properties,
                               @Qualifier("bitfinexBaseClient") BaseClient client) {
        super(parseUtil, properties, client);
    }
}
