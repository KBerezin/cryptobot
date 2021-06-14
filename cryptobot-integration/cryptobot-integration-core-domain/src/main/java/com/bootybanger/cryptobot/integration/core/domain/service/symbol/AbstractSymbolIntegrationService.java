package com.bootybanger.cryptobot.integration.core.domain.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.mapper.SymbolDTOMapper;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractSymbolIntegrationService implements ExchangeSymbolIntegrationService {
    private final ExchangeSymbolClient client;
    private final SymbolDTOMapper symbolDTOMapper;

    @Override
    public Mono<List<SymbolDTO>> getAllSymbols() {
        return client.getExchangeSymbols()
                .map(symbolDTOMapper::toSymbolDTO);
    }
}
