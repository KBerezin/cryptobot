package com.bootybanger.cryptobot.common.integration.service.cache;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AssetCacheIntegrationService {
    Mono<Map<String, Set<AssetDTO>>> getAllAssets();
    Mono<Void> addAssetList(List<AssetDTO> assetDTOList);
}
