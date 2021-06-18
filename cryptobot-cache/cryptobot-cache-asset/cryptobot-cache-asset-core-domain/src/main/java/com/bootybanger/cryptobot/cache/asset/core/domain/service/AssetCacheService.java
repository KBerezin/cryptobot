package com.bootybanger.cryptobot.cache.asset.core.domain.service;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AssetCacheService {
    Mono<Void> put(List<AssetDTO> newAssets);

    Mono<Map<String, Set<AssetDTO>>> getAssetMap();
}
