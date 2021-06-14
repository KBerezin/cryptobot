package com.bootybanger.cryptobot.integration.symbol.core.service.symbol.coin;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import core.service.catalog.CatalogCoinIntegrationService;
import core.service.symbol.CoinMarketCapCoinIntegrationService;
import core.service.symbol.CoinUpdateService;
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
    public void updateCoins() {
        Mono<List<CoinDTO>> allCoins = coinMarketCapCoinIntegrationService.getAllCoins();
        allCoins.subscribe(coinDTOList -> catalogCoinIntegrationService.addList(coinDTOList).subscribe());
    }
}
