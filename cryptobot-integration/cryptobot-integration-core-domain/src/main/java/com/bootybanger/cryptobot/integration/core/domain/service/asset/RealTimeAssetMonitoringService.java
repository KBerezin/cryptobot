package com.bootybanger.cryptobot.integration.core.domain.service.asset;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RealTimeAssetMonitoringService {
    Mono<Void> put(List<AssetDTO> newAssets);

    Mono<Map<SymbolDTO, Set<AssetDTO>>> getAssetMap();
}
