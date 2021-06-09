package com.bootybanger.cryptobot.integration.core.service.price.handler;

import com.bootybanger.cryptobot.common.constant.dto.ArbitrageWindowDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetPair;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.price.handler.ArbitrageWindowFinder;
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
    public Mono<Map<SymbolDTO, List<ArbitrageWindowDTO>>> findWindows(Mono<Map<SymbolDTO, List<AssetPair>>> assetPairMap) {
        Map<SymbolDTO, List<ArbitrageWindowDTO>> result = new ConcurrentHashMap<>();
        Mono<List<List<ArbitrageWindowDTO>>> map = assetPairMap.map(symbolDTOListMap ->
                symbolDTOListMap.values().stream()
                        .map(assetPairs ->
                                assetPairs.stream()
                                        .filter(this::containsArbitrageWindow)
                                        .map(this::factoryArbitrageWindow)
                                        .collect(Collectors.toList()))
                        .collect(Collectors.toList()));

        return map.map(lists -> {
            lists.stream()
                    .filter(list -> list.size() > 0)
                    .distinct()
                    .forEach(list -> result.put(list.get(0).getAsk().getSymbolDTO(), list));
            return result;
        });
    }

    private boolean containsArbitrageWindow(AssetPair assetPair) {
        double differencePct =
                PriceMath.calculatePriceDifferencePct(assetPair.getBid().getBid(),
                        assetPair.getAsk().getAsk());
        return PriceMath.isPriceDifferenceAppropriate(differencePct);
    }

    private ArbitrageWindowDTO factoryArbitrageWindow(AssetPair assetPair) {
        ArbitrageWindowDTO arbitrageWindowDTO = new ArbitrageWindowDTO();
        arbitrageWindowDTO.setAsk(assetPair.getAsk());
        arbitrageWindowDTO.setBid(assetPair.getBid());
        return arbitrageWindowDTO;
    }


}
