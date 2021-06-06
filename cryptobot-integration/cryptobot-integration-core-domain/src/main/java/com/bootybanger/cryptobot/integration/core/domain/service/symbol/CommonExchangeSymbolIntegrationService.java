package com.bootybanger.cryptobot.integration.core.domain.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CommonExchangeSymbolIntegrationService {
    Mono<List<SymbolDTO>> getAllSymbols();
}
