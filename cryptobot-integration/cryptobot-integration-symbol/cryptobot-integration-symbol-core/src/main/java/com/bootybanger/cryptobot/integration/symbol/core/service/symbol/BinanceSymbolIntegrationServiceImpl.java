package com.bootybanger.cryptobot.integration.symbol.core.service.symbol;

import com.bootybanger.cryptobot.common.constant.mapper.SymbolDTOMapper;
import core.service.symbol.AbstractSymbolIntegrationService;
import core.service.symbol.ExchangeSymbolClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BinanceSymbolIntegrationServiceImpl extends AbstractSymbolIntegrationService {
    public BinanceSymbolIntegrationServiceImpl(@Qualifier("binanceSymbolClient") ExchangeSymbolClient client,
                                               @Qualifier("symbolDTOToSaveMapperImpl") SymbolDTOMapper symbolDTOMapper) {
        super(client, symbolDTOMapper);
    }
}
