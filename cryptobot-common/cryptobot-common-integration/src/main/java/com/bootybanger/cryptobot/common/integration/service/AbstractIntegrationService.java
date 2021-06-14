package com.bootybanger.cryptobot.common.integration.service;

import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractIntegrationService {
    private final BaseClient client;
}
