package com.bootybanger.cryptobot.integration.symbol.core.service.catalog;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import com.bootybanger.cryptobot.integration.symbol.core.service.client.CatalogCoinClient;
import core.service.catalog.CatalogCoinIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogCoinIntegrationServiceImpl implements CatalogCoinIntegrationService {

    private final CatalogCoinClient client;

    @Override
    public Mono<Void> addList(List<CoinDTO> coinDTOList) {
        return client.addCoinList(coinDTOList);
    }

    @Override
    public Mono<List<CoinDTO>> getList() {
        return client.getAllCoins();
    }
}
