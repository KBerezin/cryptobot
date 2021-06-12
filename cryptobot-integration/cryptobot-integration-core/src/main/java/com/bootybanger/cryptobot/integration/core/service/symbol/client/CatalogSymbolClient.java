package com.bootybanger.cryptobot.integration.core.service.symbol.client;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.config.CatalogConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.service.CatalogBaseClient;
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
