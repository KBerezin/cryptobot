package com.bootybanger.cryptobot.integration.asset.core.service.client;

import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import com.bootybanger.cryptobot.common.integration.config.properties.AssetProperties;
import com.bootybanger.cryptobot.integration.asset.core.domain.service.AbstractExchangeAssetClient;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AssetParseUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class KuCoinAssetClient extends AbstractExchangeAssetClient {
    public KuCoinAssetClient(@Qualifier("kuCoinAssetParseUtil") AssetParseUtil parseUtil,
                             @Qualifier("kuCoinExchangeConfigurationProperties") AssetProperties properties,
                             @Qualifier("kuCoinBaseClient") BaseClient client) {
        super(parseUtil, properties, client);
    }
}
