package com.bootybanger.cryptobot.integration.symbol.core.service.client;

import com.bootybanger.cryptobot.common.integration.client.BaseClient;

import core.config.SymbolProperties;
import core.service.symbol.AbstractExchangeSymbolClient;
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
