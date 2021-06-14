package com.bootybanger.cryptobot.integration.symbol.core.service.symbol;

import com.bootybanger.cryptobot.common.constant.mapper.SymbolDTOMapper;
import core.service.symbol.AbstractSymbolIntegrationService;
import core.service.symbol.ExchangeSymbolClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service

public class KuCoinSymbolIntegrationServiceImpl extends AbstractSymbolIntegrationService {
    public KuCoinSymbolIntegrationServiceImpl(@Qualifier("kuCoinSymbolClient") ExchangeSymbolClient client,
                                              @Qualifier("symbolDTOToSaveMapperImpl") SymbolDTOMapper symbolDTOMapper) {
        super(client, symbolDTOMapper);
    }
}
