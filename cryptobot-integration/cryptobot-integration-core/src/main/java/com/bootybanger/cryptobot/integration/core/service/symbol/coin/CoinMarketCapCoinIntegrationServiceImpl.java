package com.bootybanger.cryptobot.integration.core.service.symbol.coin;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CoinMarketCapCoinIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.symbol.coin.client.CoinMarketCapCoinClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinMarketCapCoinIntegrationServiceImpl implements CoinMarketCapCoinIntegrationService {

    private final CoinMarketCapCoinClient client;

    @Override
    public Mono<List<CoinDTO>> getAllCoins() {
        return client.getAllCoins();
    }
}
