package com.bootybanger.cryptobot.integration.core.service.price.handler;

import com.bootybanger.cryptobot.common.constant.dto.ArbitrageWindowDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetPair;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.FacadeAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.price.handler.ArbitrageWindowFinder;
import com.bootybanger.cryptobot.integration.core.domain.service.price.handler.AssetHandler;
import com.bootybanger.cryptobot.integration.core.domain.service.price.handler.AssetSplitter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetHandlerImpl implements AssetHandler {

    @Autowired
    FacadeAssetIntegrationService facadeAssetIntegrationService;

    @Autowired
    AssetSplitter assetSplitter;

    @Autowired
    ArbitrageWindowFinder arbitrageWindowFinder;

    @Override
    @Scheduled(initialDelay = 20000, fixedDelay = 120000)
    public void handle() {
        Mono<Map<SymbolDTO, List<AssetDTO>>> activeAssetMap = facadeAssetIntegrationService.getActiveAssetMap();
        activeAssetMap
                .map(symbolDTOListMap ->
                        symbolDTOListMap.entrySet().stream()
                                .filter(entry -> entry.getValue().size() < 2)
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        Mono<Map<SymbolDTO, List<AssetPair>>> assetPairMap = assetSplitter.split(activeAssetMap);
        Mono<Map<SymbolDTO, List<ArbitrageWindowDTO>>> windows = arbitrageWindowFinder.findWindows(assetPairMap);

        windows.subscribe(symbolDTOListMap -> {
            System.out.println("------------------СТАРТ-------------------------");
            Set<SymbolDTO> symbolDTOS = symbolDTOListMap.keySet();
            symbolDTOS.stream().forEach(symbolDTO -> {

                ;


                List<ArbitrageWindowDTO> arbitrageWindowDTOList = symbolDTOListMap.get(symbolDTO);
                arbitrageWindowDTOList.forEach(arbitrageWindowDTO -> {
                    double pctDiff = PriceMath.calculatePriceDifferencePct(arbitrageWindowDTO.getBid().getBid(), arbitrageWindowDTO.getAsk().getAsk());

                    if (pctDiff > 3 && pctDiff < 30) {

                        System.out.println("Symbol: " + symbolDTO.getName());
                        System.out.println("Можно купить на бирже: " + arbitrageWindowDTO.getAsk().getExchange().name());
                        System.out.println("ЗА: " + arbitrageWindowDTO.getAsk().getAsk());
                        System.out.println("Продать на бирже: " + arbitrageWindowDTO.getBid().getExchange());
                        System.out.println("ЗА: " + arbitrageWindowDTO.getBid().getBid());
                        System.out.println("Разница в процентах составит: " + pctDiff + "%");
                        System.out.println(" ");
                        System.out.println(" ");
                    }
                });



            });
            System.out.println("------------------STOP-------------------------");
        });
    }

}
