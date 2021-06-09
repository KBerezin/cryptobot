package com.bootybanger.cryptobot.integration.core.domain.service.price.handler;

import com.bootybanger.cryptobot.common.constant.dto.ArbitrageWindowDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetPair;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface ArbitrageWindowFinder {
    Mono<Map<SymbolDTO, List<ArbitrageWindowDTO>>> findWindows(Mono<Map<SymbolDTO, List<AssetPair>>> assetPairMap);
}
