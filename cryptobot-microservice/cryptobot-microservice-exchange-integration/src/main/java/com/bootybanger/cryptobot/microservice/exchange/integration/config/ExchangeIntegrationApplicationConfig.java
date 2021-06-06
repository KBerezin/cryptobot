package com.bootybanger.cryptobot.microservice.exchange.integration.config;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.BinanceSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.KuCoinSymbolIntegrationService;
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
    KuCoinSymbolIntegrationService service;

    @Autowired
    BinanceSymbolIntegrationService service1;

    @PostConstruct
    void init() {
        Mono<List<SymbolDTO>> allSymbols = service1.getAllSymbols();
        allSymbols.subscribe(System.out::println);
    }
}
