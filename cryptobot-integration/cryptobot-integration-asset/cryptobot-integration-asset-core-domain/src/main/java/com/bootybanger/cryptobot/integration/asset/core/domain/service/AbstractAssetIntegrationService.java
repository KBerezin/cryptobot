package com.bootybanger.cryptobot.integration.asset.core.domain.service;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.mapper.AssetDTOMapper;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractAssetIntegrationService implements ExchangeAssetIntegrationService {
    private final AssetDTOMapper mapper;
    private final ExchangeAssetClient client;

    public Mono<List<AssetDTO>> getAllAssets() {
        return client.getExchangeAssetList().map(mapper::toAssetDTO);
    }
}
