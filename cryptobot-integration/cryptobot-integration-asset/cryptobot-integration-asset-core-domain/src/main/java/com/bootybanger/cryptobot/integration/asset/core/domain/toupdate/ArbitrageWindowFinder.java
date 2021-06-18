package com.bootybanger.cryptobot.integration.asset.core.domain.toupdate;

import com.bootybanger.cryptobot.common.constant.dto.ArbitrageWindowDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetPair;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface ArbitrageWindowFinder {
    Mono<Map<String, List<ArbitrageWindowDTO>>> findWindows(Mono<Map<String, List<AssetPair>>> assetPairMap);
}
