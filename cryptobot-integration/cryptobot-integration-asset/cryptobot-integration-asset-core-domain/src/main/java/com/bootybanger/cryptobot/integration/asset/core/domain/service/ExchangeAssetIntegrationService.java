package com.bootybanger.cryptobot.integration.asset.core.domain.service;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExchangeAssetIntegrationService {
    Mono<List<AssetDTO>> getAllAssets();
}
