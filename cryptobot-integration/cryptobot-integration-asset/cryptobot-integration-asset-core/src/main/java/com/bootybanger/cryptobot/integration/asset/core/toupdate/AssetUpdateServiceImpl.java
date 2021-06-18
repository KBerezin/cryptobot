package com.bootybanger.cryptobot.integration.asset.core.toupdate;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.integration.service.cache.AssetCacheIntegrationService;
import com.bootybanger.cryptobot.integration.asset.core.domain.service.AssetUpdateService;
import com.bootybanger.cryptobot.integration.asset.core.domain.service.ExchangeAssetIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AssetUpdateServiceImpl implements AssetUpdateService {

    private final AssetCacheIntegrationService assetCacheIntegrationService;
    private final List<ExchangeAssetIntegrationService> assetIntegrationServiceList;

    @Override
    @Scheduled(initialDelay = 7000, fixedDelay = 10000)
    public void updateActiveAssetMap() {
        System.out.println("запускаю сканирование активов бирж");
        Stream<Mono<List<AssetDTO>>> monoStream = assetIntegrationServiceList.stream()
                .map(ExchangeAssetIntegrationService::getAllAssets)
                .map(mono -> mono.map(assetDTOList -> {
                    assetDTOList.forEach(assetDTO -> {
                        Instant instant = Instant.now();
                        assetDTO.setTimeStamp(instant.toEpochMilli());
                    });
                    return assetDTOList;
                }));
        Flux.fromStream(monoStream).flatMap(mono ->
                mono.flatMap(assetCacheIntegrationService::addAssetList)).subscribe();
    }
}
