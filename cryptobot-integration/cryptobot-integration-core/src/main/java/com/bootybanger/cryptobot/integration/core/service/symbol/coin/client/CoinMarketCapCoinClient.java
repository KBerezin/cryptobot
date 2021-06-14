package com.bootybanger.cryptobot.integration.core.service.symbol.coin.client;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import com.bootybanger.cryptobot.common.integration.client.CoinMarketCapBaseClient;
import com.bootybanger.cryptobot.integration.core.config.properties.CoinMarketCapConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.domain.util.CoinParseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CoinMarketCapCoinClient {

    private final CoinMarketCapConfigurationProperties properties;
    private final CoinMarketCapBaseClient client;
    private final CoinParseUtil parseUtil;

    public Mono<List<CoinDTO>> getAllCoins() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), properties.getHeaders(),
                String.class, properties.getCoin().get("getAll"))
                .map(parseUtil::parseListCoins);
    }

}
