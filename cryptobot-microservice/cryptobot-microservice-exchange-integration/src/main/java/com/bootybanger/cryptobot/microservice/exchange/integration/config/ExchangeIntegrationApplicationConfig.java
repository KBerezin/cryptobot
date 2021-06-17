package com.bootybanger.cryptobot.microservice.exchange.integration.config;

import com.bootybanger.cryptobot.common.constant.mapper.SymbolDTOMapper;
import core.service.coin.CoinUpdateService;
import core.service.symbol.SymbolUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackages = {
        "com.bootybanger.cryptobot.integration.symbol.core",
        "com.bootybanger.cryptobot.integration.asset.core",
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

    @Qualifier("symbolDTOMapperImpl")
    @Autowired
    SymbolDTOMapper mapper1;

    @PostConstruct
    void init() {
        coinUpdateService.updateCoins().subscribe(
                unused -> System.out.println("----------------обновление монет ок-------------------"),
                throwable -> System.out.println("ошибка1 " + throwable.getMessage()),
                () -> {
                    symbolUpdateService.updateSymbols().subscribe(
                            unused -> System.out.println("----------------обновление символов ок-------------------"),
                            throwable -> System.out.println("ошибка2 " + throwable.getMessage()),
                            () -> {
                                System.out.println("завершено, запускаю обновление маппера символов");
                                mapper1.update();
                            });
                    System.out.println("завершено, запускаю обновление маппера");
                    mapper.update();
                });
    }

}
