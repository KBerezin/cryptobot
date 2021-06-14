package com.bootybanger.cryptobot.microservice.exchange.integration.config;

import com.bootybanger.cryptobot.common.constant.mapper.SymbolDTOMapper;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.AssetUpdateService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.BinanceAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.GateAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.KuCoinAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.RealTimeAssetMonitoringService;
import core.service.catalog.CatalogSymbolIntegrationService;
import core.service.symbol.CoinMarketCapCoinIntegrationService;
import core.service.symbol.CoinUpdateService;
import core.service.symbol.SymbolUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = {
        "com.bootybanger.cryptobot.integration.core",
        "com.bootybanger.cryptobot.integration.symbol.core"
})
public class ExchangeIntegrationApplicationConfig {

    @Autowired
    SymbolDTOMapper symbolDTOMapper;

    @Autowired
    RealTimeAssetMonitoringService realTimeAssetMonitoringService;

    @Autowired
    CatalogSymbolIntegrationService catalogSymbolIntegrationService;

    @Autowired
    BinanceAssetIntegrationService service;

    @Autowired
    KuCoinAssetIntegrationService service1;

    @Autowired
    GateAssetIntegrationService service3;

    @Autowired
    AssetUpdateService facade;

    @Autowired
    CoinMarketCapCoinIntegrationService coinMarketCapCoinIntegrationService;

    @Autowired
    CoinUpdateService coinUpdateService;

    @Autowired
    SymbolUpdateService symbolUpdateService;

    @Autowired
    SymbolDTOMapper mapper;

    @PostConstruct
    void init() throws InterruptedException {
        coinUpdateService.updateCoins();
        Thread.sleep(10000);
        symbolUpdateService.updateSymbols();
        mapper.update();
    }

}
