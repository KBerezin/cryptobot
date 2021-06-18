package com.bootybanger.cryptobot.integration.asset.core.domain.toupdate;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetPair;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AssetSplitter {
    Mono<Map<String, List<AssetPair>>> splitToPairs(Mono<Map<String, Set<AssetDTO>>> assetMap);
}
