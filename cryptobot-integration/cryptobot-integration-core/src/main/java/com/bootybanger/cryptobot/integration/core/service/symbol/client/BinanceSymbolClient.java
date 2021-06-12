package com.bootybanger.cryptobot.integration.core.service.symbol.client;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.core.config.BinanceConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.service.BinanceBaseClient;
import com.bootybanger.cryptobot.integration.core.util.ParseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@RequiredArgsConstructor
public class BinanceSymbolClient {

    private final ParseUtil parseUtil;
    private final BinanceConfigurationProperties properties;
    private final BinanceBaseClient client;

    public Mono<List<ExchangeSymbolDTO>> getBinanceSymbols() {
        Map<String, String> nodeNameMap = new HashMap<>();
        nodeNameMap.put("exchangeName", CryptoExchange.BINANCE.name());
        nodeNameMap.put("listNode", "symbols");
        nodeNameMap.put("symbolNode", "symbol");
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                String.class, properties.getSymbol().get("getAll"))
                .map(json -> parseUtil.parseListExchangeSymbols(json, nodeNameMap));

    }
}
