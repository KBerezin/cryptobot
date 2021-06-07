package com.bootybanger.cryptobot.microservice.exchange.integration.config;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.price.BinanceAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.BinanceSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CatalogSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.KuCoinSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.price.BinanceAssetIntegrationServiceImpl;
import com.bootybanger.cryptobot.integration.core.service.price.client.BinanceAssetClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;

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
        service.getAllAssets().subscribe(s -> s.forEach(System.out::println));
    }
}
