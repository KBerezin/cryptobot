package com.bootybanger.cryptobot.integration.core.domain.service.asset;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;

public interface FacadeAssetIntegrationService {
    void updateActiveAssetMap();

    Mono<Map<SymbolDTO, Set<AssetDTO>>> getActiveAssetMap();
}
