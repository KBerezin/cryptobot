package com.bootybanger.cryptobot.integration.core.service.symbol;

import com.bootybanger.cryptobot.integration.core.domain.mapper.SymbolDTOMapper;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.AbstractSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.ExchangeSymbolClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BinanceSymbolIntegrationServiceImpl extends AbstractSymbolIntegrationService {
    public BinanceSymbolIntegrationServiceImpl(@Qualifier("binanceSymbolClient") ExchangeSymbolClient client,
                                               SymbolDTOMapper symbolDTOMapper) {
        super(client, symbolDTOMapper);
    }
}
