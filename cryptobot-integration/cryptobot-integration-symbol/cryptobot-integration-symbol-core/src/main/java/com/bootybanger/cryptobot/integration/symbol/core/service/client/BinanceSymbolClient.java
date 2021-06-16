package com.bootybanger.cryptobot.integration.symbol.core.service.client;

import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import com.bootybanger.cryptobot.common.integration.config.properties.SymbolProperties;
import core.service.symbol.AbstractExchangeSymbolClient;
import core.util.SymbolParseUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BinanceSymbolClient extends AbstractExchangeSymbolClient {
    public BinanceSymbolClient(@Qualifier("binanceSymbolParseUtilImpl") SymbolParseUtil parseUtil,
                               @Qualifier("binanceExchangeConfigurationProperties") SymbolProperties symbolProperties,
                               @Qualifier("binanceBaseClient") BaseClient client) {
        super(parseUtil, symbolProperties, client);
    }
}
