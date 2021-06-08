package com.bootybanger.cryptobot.asset.monitoring.core.domain.service;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface RealTimeAssetMonitoringService {
    Mono<Void> put(List<AssetDTO> newAssets);

    Mono<Map<SymbolDTO, List<AssetDTO>>> getAssetMap();
}
