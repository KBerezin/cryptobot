package com.bootybanger.cryptobot.microservice.exchange.integration.config;

import com.bootybanger.cryptobot.common.constant.mapper.SymbolDTOMapper;
import core.service.symbol.CoinUpdateService;
import core.service.symbol.SymbolUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = {
        "com.bootybanger.cryptobot.integration.core",
        "com.bootybanger.cryptobot.integration.symbol.core",
        "com.bootybanger.cryptobot.common.integration.config"
})
public class ExchangeIntegrationApplicationConfig {

    @Autowired
    CoinUpdateService coinUpdateService;

    @Autowired
    SymbolUpdateService symbolUpdateService;

    @Qualifier("symbolDTOToSaveMapperImpl")
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
