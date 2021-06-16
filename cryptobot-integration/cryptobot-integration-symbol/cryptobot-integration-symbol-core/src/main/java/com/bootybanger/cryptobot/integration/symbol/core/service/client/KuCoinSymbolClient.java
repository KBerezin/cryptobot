package com.bootybanger.cryptobot.integration.symbol.core.service.client;

import com.bootybanger.cryptobot.common.integration.client.BaseClient;

import com.bootybanger.cryptobot.common.integration.config.properties.SymbolProperties;
import core.service.client.AbstractExchangeSymbolClient;
import core.util.SymbolParseUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class KuCoinSymbolClient extends AbstractExchangeSymbolClient {
    public KuCoinSymbolClient(@Qualifier("kuCoinParseUtil") SymbolParseUtil parseUtil,
                              @Qualifier("kuCoinExchangeConfigurationProperties") SymbolProperties symbolProperties,
                              @Qualifier("kuCoinBaseClient") BaseClient client) {
        super(parseUtil, symbolProperties, client);
    }
}
