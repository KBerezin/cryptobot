package com.bootybanger.cryptobot.integration.core.service.price.handler;

import com.bootybanger.cryptobot.common.constant.dto.ArbitrageWindowDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetPair;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.FacadeAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.RealTimeAssetMonitoringService;
import com.bootybanger.cryptobot.integration.core.domain.service.price.handler.ArbitrageWindowFinder;
import com.bootybanger.cryptobot.integration.core.domain.service.price.handler.AssetHandler;
import com.bootybanger.cryptobot.integration.core.domain.service.price.handler.AssetSplitter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetHandlerImpl implements AssetHandler {

    @Autowired
    RealTimeAssetMonitoringService realTimeAssetMonitoringService;

    @Autowired
    FacadeAssetIntegrationService facadeAssetIntegrationService;

    @Autowired
    AssetSplitter assetSplitter;

    @Autowired
    ArbitrageWindowFinder arbitrageWindowFinder;

    @Override
    @Scheduled(initialDelay = 10000, fixedDelay = 11000)
    public void handle() {
        Mono<Map<SymbolDTO, Set<AssetDTO>>> activeAssetMap = realTimeAssetMonitoringService.getAssetMap();
        activeAssetMap
                .map(symbolDTOListMap ->
                        symbolDTOListMap.entrySet().stream()
                                .filter(entry -> entry.getValue().size() < 2)
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        Mono<Map<SymbolDTO, List<AssetPair>>> assetPairMap = assetSplitter.split(activeAssetMap);
        Mono<Map<SymbolDTO, List<ArbitrageWindowDTO>>> windows = arbitrageWindowFinder.findWindows(assetPairMap);

        windows.subscribe(symbolDTOListMap -> {
            System.out.println("-------------------------СТАРТ-------------------------");
            Set<SymbolDTO> symbolDTOS = symbolDTOListMap.keySet();
            symbolDTOS.forEach(symbolDTO -> {
                if(!isExcluded(symbolDTO)) {
                    List<ArbitrageWindowDTO> arbitrageWindowDTOList = symbolDTOListMap.get(symbolDTO);
                    arbitrageWindowDTOList.forEach(arbitrageWindowDTO -> {
                        double pctDiff = PriceMath.calculatePriceDifferencePct(arbitrageWindowDTO.getAssetPair().getBid(), arbitrageWindowDTO.getAssetPair().getAsk());
                        if (pctDiff > 2 && pctDiff < 90) {
                            System.out.println("Symbol: " + symbolDTO.getName());
                            System.out.println("Можно купить на бирже: " + arbitrageWindowDTO.getAssetPair().getAskExchange());
                            System.out.println("ЗА: " + arbitrageWindowDTO.getAssetPair().getAsk());
                            System.out.println("Продать на бирже: " + arbitrageWindowDTO.getAssetPair().getBidExchange());
                            System.out.println("ЗА: " + arbitrageWindowDTO.getAssetPair().getBid());
                            System.out.println("Разница в процентах составит: " + pctDiff + "%");
                            System.out.println(" ");
                            System.out.println(" ");
                        }
                    });
                }
            });
            System.out.println("-------------------------STOP-------------------------");
        });
    }

    boolean isExcluded(SymbolDTO symbolDTO) {
        List<String> strings = Arrays.asList(
                //нет вывода или депозита
                "CELO_USDT", "ROSE_USDT","BCHA_USDT", "SERO_USDT", "COCOS_USDT",
                "GRIN_USDT", "GRIN_BTC", "GRIN_ETH", "SUN_USDT",
                "IOTA_ETH", "LSK_ETH", "IOTA_ETH", "IOTA_USDT", "IOTA_BTC", "", "HOT_USDT", "HOT_ETH",

                //маржинальная хуйня
                "BTC3L_USDT", "ETH3L_USDT", "VET3L_USDT", "ADA3L_USDT", "LTC3L_USDT", "EOS3L_USDT",

                //высокая комиссия

                //долгий перевод
                "BTG_BTC", "BTG_USDT",

                // к битку пока скип коти на кукоине другая сеть
                "COTI_BTC", "COTI_USDT", "LABS_ETH", "GAS_BTC", "DBC_BTC", "DGB_BTC"

        );
        return strings.contains(symbolDTO.getName());
    }
}

// валюты с маленькой комиссией
// MAHA RFOX KNC ALEPH LSS MTV