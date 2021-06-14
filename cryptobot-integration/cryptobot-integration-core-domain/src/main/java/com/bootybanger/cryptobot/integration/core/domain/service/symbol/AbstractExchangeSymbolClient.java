package com.bootybanger.cryptobot.integration.core.domain.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import com.bootybanger.cryptobot.integration.core.domain.config.SymbolProperties;
import com.bootybanger.cryptobot.integration.core.domain.util.SymbolParseUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractExchangeSymbolClient implements ExchangeSymbolClient {
    private final SymbolParseUtil parseUtil;
    private final SymbolProperties symbolProperties;
    private final BaseClient client;

    @Override
    public Mono<List<ExchangeSymbolDTO>> getExchangeSymbols() {
        return client.getClient(symbolProperties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                String.class, symbolProperties.getSymbol().get("getAll"))
                .map(parseUtil::parseListExchangeSymbols);
    }
}
