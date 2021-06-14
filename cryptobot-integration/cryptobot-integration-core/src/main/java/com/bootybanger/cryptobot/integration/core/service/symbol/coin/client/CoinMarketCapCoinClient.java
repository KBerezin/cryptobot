package com.bootybanger.cryptobot.integration.core.service.symbol.coin.client;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import com.bootybanger.cryptobot.integration.core.config.properties.CoinMarketCapConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.domain.util.ParseUtil;
import com.bootybanger.cryptobot.common.integration.client.CoinMarketCapBaseClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CoinMarketCapCoinClient {

    private final CoinMarketCapConfigurationProperties properties;
    private final CoinMarketCapBaseClient client;
    private final ParseUtil parseUtil;

    public Mono<List<CoinDTO>> getAllCoins() {
        Map<String, String> nodeNameMap = new HashMap<>();
        nodeNameMap.put("listNode", "data");
        nodeNameMap.put("nameNode", "name");
        nodeNameMap.put("symbolNode", "symbol");
        nodeNameMap.put("rankNode", "rank");
        nodeNameMap.put("platformNode", "platform");
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), properties.getHeaders(),
                String.class, properties.getCoin().get("getAll"))
                .map(json -> parseUtil.parseListCoins(json, nodeNameMap));
    }

}
