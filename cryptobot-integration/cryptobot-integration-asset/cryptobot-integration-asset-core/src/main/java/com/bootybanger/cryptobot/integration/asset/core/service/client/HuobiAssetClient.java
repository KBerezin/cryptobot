package com.bootybanger.cryptobot.integration.asset.core.service.client;

import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import com.bootybanger.cryptobot.common.integration.config.properties.exchange.AssetProperties;
import com.bootybanger.cryptobot.integration.asset.core.domain.service.AbstractExchangeAssetClient;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AssetParseUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HuobiAssetClient extends AbstractExchangeAssetClient {
    public HuobiAssetClient(@Qualifier("huobiAssetParseUtil") AssetParseUtil parseUtil,
                            @Qualifier("huobiExchangeConfigurationProperties") AssetProperties properties,
                            @Qualifier("huobiBaseClient") BaseClient client) {
        super(parseUtil, properties, client);
    }
}
