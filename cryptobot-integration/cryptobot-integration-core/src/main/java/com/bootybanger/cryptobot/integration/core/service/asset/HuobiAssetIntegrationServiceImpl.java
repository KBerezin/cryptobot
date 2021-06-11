package com.bootybanger.cryptobot.integration.core.service.asset;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.HuobiIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CatalogSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.asset.client.HuobiAssetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HuobiAssetIntegrationServiceImpl implements HuobiIntegrationService {

    private final CatalogSymbolIntegrationService catalogService;
    private final HuobiAssetClient client;

    @Override
    public Mono<List<AssetDTO>> getAllAssets() {
        Mono<List<SymbolDTO>> allSymbols = catalogService.getAllSymbols();
        Mono<List<AssetDTO>> huobiAssets = client.getHuobiAssets();

        return allSymbols.flatMap(symbolDTOList ->
                huobiAssets.map(huobiAssetList -> huobiAssetList.stream()
                        .filter(asset -> {
                            //TODO можно заменить когда будет парсер
                            for (SymbolDTO symbolDTO : symbolDTOList) {
                                String name = symbolDTO.getName().toLowerCase().replaceAll("_", "");
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
