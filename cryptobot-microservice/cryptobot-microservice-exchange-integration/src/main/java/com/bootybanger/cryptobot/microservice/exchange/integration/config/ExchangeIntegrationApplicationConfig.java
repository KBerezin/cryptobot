package com.bootybanger.cryptobot.microservice.exchange.integration.config;

import com.bootybanger.cryptobot.integration.core.domain.service.asset.BinanceAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.GateAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.KuCoinAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.BinanceSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CatalogSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.KuCoinSymbolIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

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

    @PostConstruct
    void init() {
/*
        Mono<List<SymbolDTO>> allSymbols = binanceSymbolIntegrationService.getAllSymbols();
        Mono<List<SymbolDTO>> allSymbols1 = kuCoinSymbolIntegrationService.getAllSymbols();

        allSymbols.subscribe(l -> catalogSymbolIntegrationService.addList(l).subscribe());
        allSymbols1.subscribe(l -> catalogSymbolIntegrationService.addList(l).subscribe());
        service.getAllAssets().subscribe(s -> s.forEach(System.out::println));
 */

        service3.getAllAssets().subscribe(s -> s.forEach(System.out::println));
    }
}
