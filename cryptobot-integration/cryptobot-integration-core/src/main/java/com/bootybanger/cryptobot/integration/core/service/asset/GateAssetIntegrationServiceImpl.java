package com.bootybanger.cryptobot.integration.core.service.asset;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.GateAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CatalogSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.asset.client.GateAssetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GateAssetIntegrationServiceImpl implements GateAssetIntegrationService {

    private final CatalogSymbolIntegrationService catalogService;
    private final GateAssetClient client;

    @Override
    public Mono<List<AssetDTO>> getAllAssets() {
        Mono<List<SymbolDTO>> allSymbols = catalogService.getAllSymbols();
        Mono<List<AssetDTO>> gateAssets = client.getGateAssets();

        return allSymbols.flatMap(symbolDTOList ->
                gateAssets.map(gateAssetList -> gateAssetList.stream()
                        .filter(asset -> {
                            //TODO можно заменить когда будет парсер
                            for (SymbolDTO symbolDTO : symbolDTOList) {
                                String name = symbolDTO.getName();
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
