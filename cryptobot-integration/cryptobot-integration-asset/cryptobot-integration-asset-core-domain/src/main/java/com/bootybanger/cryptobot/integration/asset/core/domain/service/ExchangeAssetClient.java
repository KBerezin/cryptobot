package com.bootybanger.cryptobot.integration.asset.core.domain.service;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExchangeAssetClient {
    Mono<List<ExchangeAssetDTO>> getExchangeAssetList();
}
