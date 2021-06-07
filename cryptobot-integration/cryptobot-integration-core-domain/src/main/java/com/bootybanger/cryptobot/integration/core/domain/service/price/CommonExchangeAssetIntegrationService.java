package com.bootybanger.cryptobot.integration.core.domain.service.price;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CommonExchangeAssetIntegrationService {
    Mono<List<AssetDTO>> getAllAssets();
}
