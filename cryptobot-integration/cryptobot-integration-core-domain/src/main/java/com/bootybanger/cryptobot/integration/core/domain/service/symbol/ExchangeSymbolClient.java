package com.bootybanger.cryptobot.integration.core.domain.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExchangeSymbolClient {
    Mono<List<ExchangeSymbolDTO>> getExchangeSymbols();
}
