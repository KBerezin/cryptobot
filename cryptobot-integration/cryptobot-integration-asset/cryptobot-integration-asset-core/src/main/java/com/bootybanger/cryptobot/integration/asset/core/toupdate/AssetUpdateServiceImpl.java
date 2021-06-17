package com.bootybanger.cryptobot.integration.asset.core.toupdate;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.integration.asset.core.domain.service.ExchangeAssetIntegrationService;
import com.bootybanger.cryptobot.integration.asset.core.domain.toupdate.asset.AssetUpdateService;
import com.bootybanger.cryptobot.integration.asset.core.domain.toupdate.asset.RealTimeAssetMonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssetUpdateServiceImpl implements AssetUpdateService {

    private final RealTimeAssetMonitoringService realTimeAssetMonitoringService;
    private final List<ExchangeAssetIntegrationService> assetIntegrationServiceList;

    @Override
    @Scheduled(initialDelay = 5000, fixedDelay = 10500)
    public void updateActiveAssetMap() {
        System.out.println("запускаю обновление");
        Optional<Mono<List<AssetDTO>>> monoAssetListOptional = assetIntegrationServiceList.stream()
                .map(ExchangeAssetIntegrationService::getAllAssets)
                .reduce((mono1, mono2) ->
                        mono1.flatMap(list -> mono2.map(list2 -> {
                            list2.addAll(list);
                            return list2;
                        })));
        monoAssetListOptional.ifPresent(mono -> mono.subscribe(realTimeAssetMonitoringService::put));
    }
}
