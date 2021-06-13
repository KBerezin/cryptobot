package com.bootybanger.cryptobot.integration.core.service.symbol;

import com.bootybanger.cryptobot.integration.core.service.symbol.client.SymbolClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public abstract class AbstractSymbolIntegrationService {

    private final SymbolClient client;
}
