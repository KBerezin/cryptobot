package com.bootybanger.cryptobot.common.integration.service.client;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.common.integration.client.InternalBaseClient;
import com.bootybanger.cryptobot.common.integration.config.properties.internal.CacheConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CacheAssetClient {
    private final CacheConfigurationProperties properties;
    private final InternalBaseClient client;

    public Mono<Void> addAssetList(List<AssetDTO> assetDTOList) {
        return client.postClient(properties.getBaseUrl(), assetDTOList, new HashMap<>(), new HashMap<>(),
                new ParameterizedTypeReference<>() {}, properties.getAssetCache().get("addList"));
    }

    public Mono<Map<String, Set<AssetDTO>>> getAllAssets() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                new ParameterizedTypeReference<>() {}, properties.getAssetCache().get("getAll"));
    }
}
