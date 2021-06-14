package com.bootybanger.cryptobot.integration.core.service.asset;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.mapper.AssetDTOMapper;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.BinanceAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.asset.client.BinanceAssetClient;
import core.service.catalog.CatalogSymbolIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BinanceAssetIntegrationServiceImpl implements BinanceAssetIntegrationService {

    private final AssetDTOMapper mapper;
    private final CatalogSymbolIntegrationService catalogService;
    private final BinanceAssetClient client;

    @Override
    public Mono<List<AssetDTO>> getAllAssets() {
        return client.getBinanceAssets().map(mapper::toAssetDTO);
    }
}
