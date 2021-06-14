package com.bootybanger.cryptobot.integration.core.domain.service.symbol;

import com.bootybanger.cryptobot.common.integration.service.AbstractIntegrationService;
import com.bootybanger.cryptobot.common.integration.client.BaseClient;

public abstract class AbstractSymbolIntegrationService extends AbstractIntegrationService {
    public AbstractSymbolIntegrationService(BaseClient client) {
        super(client);
    }
}
