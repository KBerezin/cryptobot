package com.bootybanger.cryptobot.integration.core.service.asset.client;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.common.integration.client.GateBaseClient;

import com.bootybanger.cryptobot.common.integration.config.properties.GateConfigurationProperties;
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

    public Mono<List<ExchangeAssetDTO>> getGateAssets() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                String.class, properties.getAsset().get("getAll"))
                .map(this::parseGateAssetListJson);
    }

    private List<ExchangeAssetDTO> parseGateAssetListJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ExchangeAssetDTO> exchangeAssetDTOList = new CopyOnWriteArrayList<>();
        try {
            JsonNode assetListNode = objectMapper.readTree(json);
            assetListNode.forEach(assetNode -> {
                String symbol = assetNode.get("currency_pair").asText().replaceAll("_", "");
                double bid = assetNode.get("highest_bid").asDouble();
                double ask = assetNode.get("lowest_ask").asDouble();

                //TODO можно убрать когда появится исключение символов
                if (bid != 0 && ask != 0) {
                    exchangeAssetDTOList.add(ExchangeAssetDTO.builder()
                            .exchangeSymbolDTO(ExchangeSymbolDTO.builder().symbol(symbol).build())
                            .exchange(CryptoExchange.GATE)
                            .bestBid(bid)
                            .bestAsk(ask)
                            .build());
                }
            });
        } catch (JsonProcessingException e) {
            //TODO логгер
            e.printStackTrace();
        }
        return exchangeAssetDTOList;
    }
}
