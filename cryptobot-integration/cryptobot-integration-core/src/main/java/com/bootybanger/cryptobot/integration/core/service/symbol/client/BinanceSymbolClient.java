package com.bootybanger.cryptobot.integration.core.service.symbol.client;

import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import com.bootybanger.cryptobot.integration.core.domain.config.SymbolProperties;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.AbstractExchangeSymbolClient;
import com.bootybanger.cryptobot.integration.core.domain.util.SymbolParseUtil;
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
