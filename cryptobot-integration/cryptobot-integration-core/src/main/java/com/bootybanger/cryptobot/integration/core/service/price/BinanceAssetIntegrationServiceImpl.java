package com.bootybanger.cryptobot.integration.core.service.price;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.price.BinanceAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CatalogSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.price.client.BinanceAssetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BinanceAssetIntegrationServiceImpl implements BinanceAssetIntegrationService {

    private final CatalogSymbolIntegrationService catalogService;
    private final BinanceAssetClient client;

    @Override
    public Mono<List<AssetDTO>> getAllAssets() {
        return null;
    }
}
