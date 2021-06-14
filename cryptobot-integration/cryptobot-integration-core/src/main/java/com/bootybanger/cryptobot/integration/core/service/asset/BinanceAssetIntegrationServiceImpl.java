package com.bootybanger.cryptobot.integration.core.service.asset;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.BinanceAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.asset.client.BinanceAssetClient;
import core.service.catalog.CatalogSymbolIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BinanceAssetIntegrationServiceImpl implements BinanceAssetIntegrationService {

    private final CatalogSymbolIntegrationService catalogService;
    private final BinanceAssetClient client;

    @Override
    public Mono<List<AssetDTO>> getAllAssets() {
        Mono<List<SymbolDTO>> allSymbols = catalogService.getAllSymbols();
        Mono<List<AssetDTO>> binanceAssets = client.getBinanceAssets();

        return allSymbols.flatMap(symbolDTOList ->
                binanceAssets.map(binanceAssetList -> binanceAssetList.stream()
                        .filter(asset -> {
                            //TODO можно заменить когда будет парсер
                            for (SymbolDTO symbolDTO : symbolDTOList) {
                                String name = symbolDTO.getName().replaceAll("_", "");
                                if (name.equals(asset.getSymbolDTO().getName())) {
                                    asset.setSymbolDTO(symbolDTO);
                                    return true;
                                }
                            }
                            return false;
                        })
                        .collect(Collectors.toList())));
    }
}
