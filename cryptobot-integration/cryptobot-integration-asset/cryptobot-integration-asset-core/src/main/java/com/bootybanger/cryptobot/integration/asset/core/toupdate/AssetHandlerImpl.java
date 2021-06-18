package com.bootybanger.cryptobot.integration.asset.core.toupdate;

import com.bootybanger.cryptobot.common.constant.dto.ArbitrageWindowDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.AssetPair;
import com.bootybanger.cryptobot.common.constant.util.PriceMath;
import com.bootybanger.cryptobot.common.integration.service.cache.AssetCacheIntegrationService;
import com.bootybanger.cryptobot.integration.asset.core.domain.toupdate.ArbitrageWindowFinder;
import com.bootybanger.cryptobot.integration.asset.core.domain.toupdate.AssetHandler;
import com.bootybanger.cryptobot.integration.asset.core.domain.toupdate.AssetSplitter;
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
    AssetCacheIntegrationService assetCacheIntegrationService;

    @Autowired
    AssetSplitter assetSplitter;

    @Autowired
    ArbitrageWindowFinder arbitrageWindowFinder;

    @Override
    @Scheduled(initialDelay = 15000, fixedDelay = 10000)
    public void handle() {
        System.out.println("запускаю обработку");
        Mono<Map<String, Set<AssetDTO>>> activeAssetMap = assetCacheIntegrationService.getAllAssets();
        activeAssetMap
                .map(symbolDTOListMap ->
                        symbolDTOListMap.entrySet().stream()
                                .filter(entry -> entry.getValue().size() < 2)
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        Mono<Map<String, List<AssetPair>>> assetPairMap = assetSplitter.splitToPairs(activeAssetMap);
        Mono<Map<String, List<ArbitrageWindowDTO>>> windows = arbitrageWindowFinder.findWindows(assetPairMap);

        windows.subscribe(symbolDTOListMap -> {
            System.out.println("-------------------------СТАРТ-------------------------");
            Set<String> symbolNames = symbolDTOListMap.keySet();
            symbolNames.forEach(symbolName -> {
                if(!isExcluded(symbolName)) {
                    List<ArbitrageWindowDTO> arbitrageWindowDTOList = symbolDTOListMap.get(symbolName);
                    arbitrageWindowDTOList.forEach(arbitrageWindowDTO -> {
                        double pctDiff = PriceMath.calculatePriceDifferencePct(arbitrageWindowDTO.getAssetPair().getBid(), arbitrageWindowDTO.getAssetPair().getAsk());
                        if (pctDiff > 0) {
                            System.out.println("Symbol: " + symbolName);
                            System.out.println("ID: " + arbitrageWindowDTO.getAssetPair().getSymbolDTO().getId());
                            System.out.println("RANK: " + arbitrageWindowDTO.getAssetPair().getSymbolDTO().getBaseAsset().getRank());
                            System.out.println("NET: " + arbitrageWindowDTO.getAssetPair().getSymbolDTO().getBaseAsset().getPlatform());
                            System.out.println("Name: " + arbitrageWindowDTO.getAssetPair().getSymbolDTO().getBaseAsset().getName());
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

    boolean isExcluded(String symbolName) {
        List<String> strings = Arrays.asList(
                //нет вывода или депозита
                "CELO_USDT", "ROSE_USDT","BCHA_USDT", "SERO_USDT", "COCOS_USDT",
                "GRIN_USDT", "GRIN_BTC", "GRIN_ETH", "SUN_USDT",
                "LSK_ETH",

                "BTG_USDT", "COTI_USDT", "COTI_BTC", "BTG_BTC", "GTC_BTC", "TRB_USDT", "PNT_BTC", "STC_USDT", "SUN_BTC",
                "GTC_USDT", "GAS_BTC", "GAS_USDT", "GAS_ETH", "IOTA_BTC", "IOTA_USDT", "IOTA_ETH",
                "MAN_BTC", "COT_IBTC", "GT_CUSDT", "LSK_BTC", "MDX_BTC", "XVG_ETH", "MDX_USDT", "AE_ETH", "", "", "", "", "", "", "", "", "", "", "", "", ""





                );
        return strings.contains(symbolName);
    }
}

// валюты с маленькой комиссией
// MAHA RFOX KNC ALEPH LSS MTV