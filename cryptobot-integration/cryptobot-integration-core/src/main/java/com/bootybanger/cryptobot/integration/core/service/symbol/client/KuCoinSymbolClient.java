package com.bootybanger.cryptobot.integration.core.service.symbol.client;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.core.config.KuCoinConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.service.KuCoinBaseClient;
import com.bootybanger.cryptobot.integration.core.util.ParseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KuCoinSymbolClient {

    private final ParseUtil parseUtil;
    private final KuCoinConfigurationProperties properties;
    private final KuCoinBaseClient client;

    public Mono<List<ExchangeSymbolDTO>> getKuCoinSymbols() {
        Map<String, String> nodeNameMap = new HashMap<>();
        nodeNameMap.put("exchangeName", CryptoExchange.KUCOIN.name());
        nodeNameMap.put("listNode", "ticker");
        nodeNameMap.put("symbolNode", "symbol");
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(), String.class,
                properties.getSymbol().get("getAll"))
                .map(json -> parseUtil.parseListExchangeSymbols(json, nodeNameMap));
    }
}
