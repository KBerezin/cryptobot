package com.bootybanger.cryptobot.integration.core.domain.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CatalogCoinIntegrationService {
    Mono<Void> addList(List<CoinDTO> coinDTOList);
    Mono<List<CoinDTO>> getList();
}
