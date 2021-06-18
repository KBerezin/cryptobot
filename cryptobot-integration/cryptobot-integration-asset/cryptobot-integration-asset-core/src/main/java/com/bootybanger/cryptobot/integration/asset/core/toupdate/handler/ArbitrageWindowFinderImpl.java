package com.bootybanger.cryptobot.integration.asset.core.toupdate.handler;

import com.bootybanger.cryptobot.common.constant.dto.ArbitrageWindowDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetPair;
import com.bootybanger.cryptobot.common.constant.util.PriceMath;
import com.bootybanger.cryptobot.integration.asset.core.domain.toupdate.ArbitrageWindowFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArbitrageWindowFinderImpl implements ArbitrageWindowFinder {
    @Override
    public Mono<Map<String, List<ArbitrageWindowDTO>>> findWindows(Mono<Map<String, List<AssetPair>>> assetPairMap) {
        return assetPairMap
                .flatMap(this::findArbitrageWindows);
    }

    private Mono<Map<String, List<ArbitrageWindowDTO>>> findArbitrageWindows(Map<String, List<AssetPair>> symbolDTOListMap) {
        Map<String, List<ArbitrageWindowDTO>> result = new ConcurrentHashMap<>();
        return Mono.just(symbolDTOListMap.values().stream()
                .map(assetPairs ->
                        assetPairs.stream()
                                .filter(this::containsArbitrageWindow)
                                .map(this::factoryArbitrageWindow)
                                .collect(Collectors.toList()))
                .collect(Collectors.toList()))
                .map(lists -> {
                    lists.stream()
                            .filter(list -> list.size() > 0)
                            .forEach(list -> result.put(list.get(0).getAssetPair().getSymbolDTO().getName(), list));
                    return result;
                });
    }

    private boolean containsArbitrageWindow(AssetPair assetPair) {
        double differencePct =
                PriceMath.calculatePriceDifferencePct(assetPair.getBid(),
                        assetPair.getAsk());
        return PriceMath.isPriceDifferenceAppropriate(differencePct);
    }

    private ArbitrageWindowDTO factoryArbitrageWindow(AssetPair assetPair) {
        ArbitrageWindowDTO arbitrageWindowDTO = new ArbitrageWindowDTO();
        arbitrageWindowDTO.setAssetPair(assetPair);
        return arbitrageWindowDTO;
    }


}
