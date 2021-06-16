package com.bootybanger.cryptobot.integration.symbol.core.service.client;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;

import com.bootybanger.cryptobot.common.integration.client.CatalogBaseClient;
import com.bootybanger.cryptobot.common.integration.config.properties.CatalogConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CatalogSymbolClient {

    private final CatalogConfigurationProperties properties;
    private final CatalogBaseClient client;

    public Mono<Void> addSymbolList(List<SymbolDTO> symbolDTOList) {
        return client.postClient(properties.getBaseUrl(), symbolDTOList, new HashMap<>(), new HashMap<>(),
                new ParameterizedTypeReference<>() {}, properties.getSymbol().get("addList"));
    }

    public Mono<List<SymbolDTO>> getAllSymbols() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                new ParameterizedTypeReference<>() {}, properties.getSymbol().get("getAll"));
    }

}
