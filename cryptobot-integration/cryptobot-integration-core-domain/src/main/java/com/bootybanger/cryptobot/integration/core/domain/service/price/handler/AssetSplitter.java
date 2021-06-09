package com.bootybanger.cryptobot.integration.core.domain.service.price.handler;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetPair;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface AssetSplitter {
    Mono<Map<SymbolDTO, List<AssetPair>>> split(Mono<Map<SymbolDTO, List<AssetDTO>>> assetMap);
}
