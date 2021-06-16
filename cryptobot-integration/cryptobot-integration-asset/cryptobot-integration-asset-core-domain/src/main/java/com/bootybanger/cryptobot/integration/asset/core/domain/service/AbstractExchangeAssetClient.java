package com.bootybanger.cryptobot.integration.asset.core.domain.service;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
import com.bootybanger.cryptobot.common.integration.client.BaseClient;
import com.bootybanger.cryptobot.common.integration.config.properties.AssetProperties;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AssetParseUtil;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractExchangeAssetClient implements ExchangeAssetClient {

    private final AssetParseUtil parseUtil;
    private final AssetProperties properties;
    private final BaseClient client;

    @Override
    public Mono<List<ExchangeAssetDTO>> getExchangeAssetList() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                String.class, properties.getAsset().get("getAll"))
                .map(parseUtil::parseAssetListJson);
    }
}
