package com.bootybanger.cryptobot.microservice.exchange.integration.config;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.BinanceAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.FacadeAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.GateAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.KuCoinAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.BinanceSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CatalogSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.KuCoinSymbolIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {
        "com.bootybanger.cryptobot.integration.core"
})
public class ExchangeIntegrationApplicationConfig {

    @Autowired
    CatalogSymbolIntegrationService catalogSymbolIntegrationService;

    @Autowired
    BinanceAssetIntegrationService service;

    @Autowired
    KuCoinAssetIntegrationService service1;

    @Autowired
    GateAssetIntegrationService service3;

    @Autowired
    BinanceSymbolIntegrationService binanceSymbolIntegrationService;

    @Autowired
    KuCoinSymbolIntegrationService kuCoinSymbolIntegrationService;

    @Autowired
    FacadeAssetIntegrationService facade;

    @PostConstruct
    void init() {
/*



        service.getAllAssets().subscribe(s -> s.forEach(System.out::println));
        service3.getAllAssets().subscribe(s -> s.forEach(System.out::println));


        facade.updateActiveAssetMap();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Mono<Map<SymbolDTO, List<AssetDTO>>> activeAssetMap = facade.getActiveAssetMap();
        activeAssetMap.subscribe(System.out::println);
 */
        Mono<List<SymbolDTO>> allSymbols = kuCoinSymbolIntegrationService.getAllSymbols();
        Mono<List<SymbolDTO>> allSymbols1 = binanceSymbolIntegrationService.getAllSymbols();


        allSymbols.subscribe(l -> catalogSymbolIntegrationService.addList(l).subscribe());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        allSymbols1.subscribe(l -> catalogSymbolIntegrationService.addList(l).subscribe());

    }

}
