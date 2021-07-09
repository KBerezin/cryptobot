package com.bootybanger.cryptobot.common.integration.service.cache;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.integration.service.client.CacheAssetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AssetCacheIntegrationServiceImpl implements AssetCacheIntegrationService {

    private final CacheAssetClient client;

    @Override
    public Mono<Map<String, Set<AssetDTO>>> getAllAssets() {
        return client.getAllAssets();
    }

    @Override
    public Mono<Void> addAssetList(List<AssetDTO> assetDTOList) {
        return client.addAssetList(assetDTOList);
    }
}
