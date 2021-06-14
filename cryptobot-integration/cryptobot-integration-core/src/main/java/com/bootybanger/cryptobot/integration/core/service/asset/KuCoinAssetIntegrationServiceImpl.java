package com.bootybanger.cryptobot.integration.core.service.asset;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.KuCoinAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.asset.client.KuCoinAssetClient;
import core.service.catalog.CatalogSymbolIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KuCoinAssetIntegrationServiceImpl implements KuCoinAssetIntegrationService {

    private final CatalogSymbolIntegrationService catalogSymbolIntegrationService;
    private final KuCoinAssetClient client;

    @Override
    public Mono<List<AssetDTO>> getAllAssets() {
        Mono<List<SymbolDTO>> allSymbols = catalogSymbolIntegrationService.getAllSymbols();
        Mono<List<AssetDTO>> kuCoinAssets = client.getKuCoinAssets();

        return allSymbols.flatMap(symbolDTOList ->
                kuCoinAssets.map(kuCoinAssetList -> kuCoinAssetList.stream()
                        .filter(asset -> {
                            //TODO можно заменить когда будет парсер
                            for (SymbolDTO symbolDTO : symbolDTOList) {
                                String catalogSymbolName = symbolDTO.getName();
                                if (catalogSymbolName.equals(asset.getSymbolDTO().getName())) {
                                    asset.setSymbolDTO(symbolDTO);
                                    return true;
                                }
                            }
                            return false;
                        })
                        .collect(Collectors.toList())));
    }
}
