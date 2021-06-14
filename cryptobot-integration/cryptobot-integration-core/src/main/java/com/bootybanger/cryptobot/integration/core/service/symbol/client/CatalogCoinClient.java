package com.bootybanger.cryptobot.integration.core.service.symbol.client;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import com.bootybanger.cryptobot.integration.core.config.properties.CatalogConfigurationProperties;
import com.bootybanger.cryptobot.common.integration.client.CatalogBaseClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CatalogCoinClient {

    private final CatalogConfigurationProperties properties;
    private final CatalogBaseClient client;

    public Mono<Void> addCoinList(List<CoinDTO> coinDTOList) {
        return client.postClient(properties.getBaseUrl(), coinDTOList, new HashMap<>(), new HashMap<>(),
                new ParameterizedTypeReference<>() {}, properties.getCoin().get("addList"));
    }

    public Mono<List<CoinDTO>> getAllCoins() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                new ParameterizedTypeReference<>() {}, properties.getCoin().get("getAll"));
    }
}
