package com.bootybanger.cryptobot.integration.asset.core.service.client;

import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import com.bootybanger.cryptobot.common.integration.config.properties.exchange.AssetProperties;
import com.bootybanger.cryptobot.integration.asset.core.domain.service.AbstractExchangeAssetClient;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AssetParseUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BinanceAssetClient extends AbstractExchangeAssetClient {
    public BinanceAssetClient(@Qualifier("binanceAssetParseUtil") AssetParseUtil parseUtil,
                              @Qualifier("binanceExchangeConfigurationProperties") AssetProperties properties,
                              @Qualifier("binanceBaseClient") BaseClient client) {
        super(parseUtil, properties, client);
    }
}
