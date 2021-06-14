package com.bootybanger.cryptobot.integration.core.domain.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.core.domain.config.SymbolProperties;
import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import com.bootybanger.cryptobot.integration.core.domain.util.ParseUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public abstract class AbstractExchangeSymbolClient implements ExchangeSymbolClient {
    private final ParseUtil parseUtil;
    private final SymbolProperties symbolProperties;
    private final BaseClient client;

    @Override
    public Mono<List<ExchangeSymbolDTO>> getExchangeSymbols() {
        Map<String, String> nodeNameMap = new HashMap<>();
        nodeNameMap.put("exchangeName", CryptoExchange.BINANCE.name());
        nodeNameMap.put("listNode", "symbols");
        nodeNameMap.put("symbolNode", "symbol");
        return client.getClient(symbolProperties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                String.class, symbolProperties.getSymbol().get("getAll"))
                .map(json -> parseUtil.parseListExchangeSymbols(json, nodeNameMap));
    }
}
