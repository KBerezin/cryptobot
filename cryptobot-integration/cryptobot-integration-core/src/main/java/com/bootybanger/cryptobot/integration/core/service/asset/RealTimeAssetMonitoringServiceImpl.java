package com.bootybanger.cryptobot.integration.core.service.asset;


import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.RealTimeAssetMonitoringService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class RealTimeAssetMonitoringServiceImpl implements RealTimeAssetMonitoringService {
    private final Map<SymbolDTO, List<AssetDTO>> assetMap = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> put(List<AssetDTO> newAssets) {
        newAssets.forEach(assetDTO -> {
            List<AssetDTO> newAssetDtoList = new CopyOnWriteArrayList<>();
            newAssetDtoList.add(assetDTO);
            assetMap.merge(assetDTO.getSymbolDTO(), newAssetDtoList, (ol, nl) -> {
                ol.addAll(nl);
                return ol;
            });
        });
        return Mono.empty();
    }

    @Override
    public Mono<Map<SymbolDTO, List<AssetDTO>>> getAssetMap() {
        return Mono.just(Collections.unmodifiableMap(assetMap));
    }

}
