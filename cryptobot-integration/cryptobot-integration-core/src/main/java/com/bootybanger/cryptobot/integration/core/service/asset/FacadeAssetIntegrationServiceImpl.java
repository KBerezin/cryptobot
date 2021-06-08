package com.bootybanger.cryptobot.integration.core.service.asset;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.CommonExchangeAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.FacadeAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.RealTimeAssetMonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacadeAssetIntegrationServiceImpl implements FacadeAssetIntegrationService {

    private final RealTimeAssetMonitoringService realTimeAssetMonitoringService;
    private final List<CommonExchangeAssetIntegrationService> assetIntegrationServiceList;

    @Override
    public void updateActiveAssetMap() {
        Optional<Mono<List<AssetDTO>>> monoAssetListOptional = assetIntegrationServiceList.stream()
                .map(CommonExchangeAssetIntegrationService::getAllAssets)
                .reduce((mono1, mono2) ->
                        mono1.flatMap(list -> mono2.map(list2 -> {
                            list2.addAll(list);
                            return list2;
                        })));
        monoAssetListOptional.ifPresent(mono -> mono.subscribe(realTimeAssetMonitoringService::put));
    }

    @Override
    public Mono<Map<SymbolDTO, List<AssetDTO>>> getActiveAssetMap() {
        return realTimeAssetMonitoringService.getAssetMap();
    }
}
