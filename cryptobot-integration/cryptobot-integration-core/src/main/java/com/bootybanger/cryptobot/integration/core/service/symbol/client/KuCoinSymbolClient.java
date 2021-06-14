package com.bootybanger.cryptobot.integration.core.service.symbol.client;

import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import com.bootybanger.cryptobot.integration.core.domain.config.SymbolProperties;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.AbstractExchangeSymbolClient;
import com.bootybanger.cryptobot.integration.core.domain.util.SymbolParseUtil;
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
