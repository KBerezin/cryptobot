package com.bootybanger.cryptobot.integration.asset.core.toupdate;

import com.bootybanger.cryptobot.common.constant.dto.ArbitrageWindowDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetPair;
import com.bootybanger.cryptobot.common.constant.util.PriceMath;
import com.bootybanger.cryptobot.integration.asset.core.domain.toupdate.ArbitrageWindowFinder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ArbitrageWindowFinderImpl implements ArbitrageWindowFinder {
    @Override
    public Mono<Map<String, List<ArbitrageWindowDTO>>> findWindows(Mono<Map<String, List<AssetPair>>> assetPairMap) {
        return assetPairMap.flatMap(this::search);
    }

    private Mono<Map<String, List<ArbitrageWindowDTO>>> search(Map<String, List<AssetPair>> symbolDTOListMap) {
        Map<String, List<ArbitrageWindowDTO>> arbitrageWindowMap = new ConcurrentHashMap<>();
        symbolDTOListMap.forEach((symbolName, assetPairs) -> {
            List<ArbitrageWindowDTO> arbitrageWindowDTOList = assetPairs.stream()
                    .filter(this::containsArbitrageWindow)
                    .map(this::arbitrageWindowFactoryMethod)
                    .collect(Collectors.toList());
            if (arbitrageWindowDTOList.size() > 0) {
                arbitrageWindowMap.put(symbolName, arbitrageWindowDTOList);
            }
        });
        return Mono.just(arbitrageWindowMap);
    }

    private boolean containsArbitrageWindow(AssetPair assetPair) {
        double differencePct = PriceMath.calculatePriceDifferencePct(assetPair.getBid(),
                        assetPair.getAsk());
        return PriceMath.isPriceDifferenceAppropriate(differencePct);
    }

    private ArbitrageWindowDTO arbitrageWindowFactoryMethod(AssetPair assetPair) {
        ArbitrageWindowDTO arbitrageWindowDTO = new ArbitrageWindowDTO();
        arbitrageWindowDTO.setAssetPair(assetPair);
        return arbitrageWindowDTO;
    }
}
