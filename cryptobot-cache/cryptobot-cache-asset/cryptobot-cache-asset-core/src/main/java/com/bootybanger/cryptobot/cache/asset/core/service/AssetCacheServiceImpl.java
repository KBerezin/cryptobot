package com.bootybanger.cryptobot.cache.asset.core.service;

import com.bootybanger.cryptobot.cache.asset.core.domain.service.AssetCacheService;
import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AssetCacheServiceImpl implements AssetCacheService {
    private final Map<String, Set<AssetDTO>> assetMap = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> put(List<AssetDTO> newAssets) {
        newAssets.forEach(assetDTO -> {
            Set<AssetDTO> newAssetDtoList = Collections.newSetFromMap(new ConcurrentHashMap<>());
            newAssetDtoList.add(assetDTO);
            assetMap.merge(assetDTO.getSymbolDTO().getName(), newAssetDtoList, (ns, os) -> {
                os.addAll(ns);
                return os;
            });
        });
        return Mono.empty();
    }

    @Override
    public Mono<Map<String, Set<AssetDTO>>> getAssetMap() {
        removeOldRecords();
        return Mono.just(Collections.unmodifiableMap(assetMap));
    }

    private void removeOldRecords() {
        assetMap.forEach((key, value) -> value.removeIf(assetDTO -> {
            Instant instant = Instant.now();
            long timeStampNow = instant.toEpochMilli();
            if (timeStampNow - assetDTO.getTimeStamp() > 12000) {
                System.out.println("Удаляю " + assetDTO);
            }
            return timeStampNow - assetDTO.getTimeStamp() > 12000;
        }));
    }
}
