package com.bootybanger.cryptobot.integration.symbol.core.service.coin;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import com.bootybanger.cryptobot.common.integration.service.catalog.CatalogCoinIntegrationService;
import core.service.coin.CoinMarketCapCoinIntegrationService;
import core.service.coin.CoinUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinUpdateServiceImpl implements CoinUpdateService {

    private final CoinMarketCapCoinIntegrationService coinMarketCapCoinIntegrationService;
    private final CatalogCoinIntegrationService catalogCoinIntegrationService;

    @Override
    public Mono<Void> updateCoins() {
        Mono<List<CoinDTO>> allCoins = coinMarketCapCoinIntegrationService.getAllCoins();
        return allCoins.flatMap(catalogCoinIntegrationService::addList);
    }
}
