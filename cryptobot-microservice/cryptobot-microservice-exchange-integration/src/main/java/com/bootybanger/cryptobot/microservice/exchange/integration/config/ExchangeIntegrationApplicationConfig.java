package com.bootybanger.cryptobot.microservice.exchange.integration.config;

import com.bootybanger.cryptobot.integration.core.domain.mapper.SymbolDTOMapper;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.AssetUpdateService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.BinanceAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.GateAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.KuCoinAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.RealTimeAssetMonitoringService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CatalogSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CoinMarketCapCoinIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CoinUpdateService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.SymbolUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = {
        "com.bootybanger.cryptobot.integration.core",
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

    @PostConstruct
    void init() throws InterruptedException {
        coinUpdateService.updateCoins();
        Thread.sleep(10000);
        symbolUpdateService.updateSymbols();
        symbolDTOMapper.updateCoins();
    }

}
