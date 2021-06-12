package com.bootybanger.cryptobot.integration.core.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.mapper.symbol.SymbolDTOMapper;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.BinanceSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.symbol.client.BinanceSymbolClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BinanceSymbolIntegrationServiceImpl implements BinanceSymbolIntegrationService {

    private final BinanceSymbolClient client;
    private final SymbolDTOMapper symbolDTOMapper;

    @Override
    public Mono<List<SymbolDTO>> getAllSymbols() {
        return client.getBinanceSymbols()
                .map(symbolDTOMapper::toSymbolDTO);
    }

}
