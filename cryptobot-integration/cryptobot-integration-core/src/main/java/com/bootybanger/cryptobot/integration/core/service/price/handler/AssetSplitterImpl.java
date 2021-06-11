package com.bootybanger.cryptobot.integration.core.service.price.handler;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetPair;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.price.handler.AssetSplitter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetSplitterImpl implements AssetSplitter {
    @Override
    public Mono<Map<SymbolDTO, List<AssetPair>>> split(Mono<Map<SymbolDTO, Set<AssetDTO>>> assetMap) {
        Map<SymbolDTO, List<AssetPair>> result = new ConcurrentHashMap<>();
        return assetMap.map(symbolDTOListMap -> symbolDTOListMap.values().stream()
                .filter(assetDTOSet -> assetDTOSet.size() > 1)
                .map(assetDTOSet -> {
                    List<AssetDTO> assetDTOList = new CopyOnWriteArrayList<>(assetDTOSet);
                    List<AssetPair> assetPairs = new CopyOnWriteArrayList<>();
                    for (int i = 0; i + 1 < assetDTOSet.size(); i++) {
                        for (int j = i + 1; j < assetDTOSet.size(); j++) {
                            AssetPair assetPair = getAssetPair(assetDTOList.get(i), assetDTOList.get(j));
                            AssetPair assetPair1 = getAssetPair(assetDTOList.get(j), assetDTOList.get(i));
                            assetPairs.add(assetPair);
                            assetPairs.add(assetPair1);
                        }
                    }
                    return assetPairs;
                })
                .collect(Collectors.toList()))
                .map(lists -> {
                    lists.forEach(assetPairs -> result.put(assetPairs.get(0).getSymbolDTO(), assetPairs));
                    return result;
                });
    }

    private AssetPair getAssetPair(AssetDTO assetDTO1, AssetDTO assetDTO2) {
        AssetPair assetPair = new AssetPair();
        assetPair.setSymbolDTO(assetDTO1.getSymbolDTO());
        assetPair.setAsk(assetDTO1.getAsk());
        assetPair.setAskExchange(assetDTO1.getExchange());
        assetPair.setBid(assetDTO2.getBid());
        assetPair.setBidExchange(assetDTO2.getExchange());
        return assetPair;
    }

}
