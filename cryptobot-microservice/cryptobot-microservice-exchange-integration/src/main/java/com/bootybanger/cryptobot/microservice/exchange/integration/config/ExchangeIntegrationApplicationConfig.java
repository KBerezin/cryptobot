package com.bootybanger.cryptobot.microservice.exchange.integration.config;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
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
    BinanceAssetClient assetClient;

    @PostConstruct
    void init() {
        Mono<List<ExchangeAssetDTO>> binanceAssets = assetClient.getBinanceAssets();
        binanceAssets.subscribe(System.out::println);
    }
}
