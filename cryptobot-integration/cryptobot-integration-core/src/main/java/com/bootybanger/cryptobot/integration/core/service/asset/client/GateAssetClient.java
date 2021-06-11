package com.bootybanger.cryptobot.integration.core.service.asset.client;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.core.config.GateConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.service.BaseClient;
import com.bootybanger.cryptobot.integration.core.service.GateBaseClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@RequiredArgsConstructor
public class GateAssetClient {

    private final GateConfigurationProperties properties;
    private final GateBaseClient client;

    public Mono<List<AssetDTO>> getGateAssets() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                String.class, properties.getAsset().get("getAll"))
                .map(this::parseGateAssetListJson);
    }

    private List<AssetDTO> parseGateAssetListJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AssetDTO> assetDTOList = new CopyOnWriteArrayList<>();
        try {
            JsonNode assetListNode = objectMapper.readTree(json);
            assetListNode.forEach(assetNode -> {
                String symbol = assetNode.get("currency_pair").asText();
                double bid = assetNode.get("highest_bid").asDouble();
                double ask = assetNode.get("lowest_ask").asDouble();

                //TODO можно убрать когда появится исключение символов
                if (bid != 0 && ask != 0) {
                    assetDTOList.add(AssetDTO.builder()
                            .symbolDTO(new SymbolDTO(null, symbol))
                            .exchange(CryptoExchange.GATE)
                            .bid(bid)
                            .ask(ask)
                            .build());
                }
            });
        } catch (JsonProcessingException e) {
            //TODO логгер
            e.printStackTrace();
        }
        return assetDTOList;
    }
}
