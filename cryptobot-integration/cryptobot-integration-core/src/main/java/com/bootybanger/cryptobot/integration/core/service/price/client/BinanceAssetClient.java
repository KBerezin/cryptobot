package com.bootybanger.cryptobot.integration.core.service.price.client;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.core.config.BinanceConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.service.BaseClient;
import com.bootybanger.cryptobot.integration.core.service.BinanceBaseClient;
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
public class BinanceAssetClient extends BaseClient {

    private final BinanceConfigurationProperties properties;
    private final BinanceBaseClient client;

    public Mono<List<AssetDTO>> getBinanceAssets() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                String.class, properties.getAsset().get("getAll"))
                .map(this::getAssets);
    }


    //TODO srp
    private List<AssetDTO> getAssets(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AssetDTO> assetDTOList = new CopyOnWriteArrayList<>();
        try {
            JsonNode assetListNode = objectMapper.readTree(json);
            assetListNode.forEach(assetNode -> {
                String symbol = assetNode.get("symbol").asText();
                double bid = assetNode.get("bidPrice").asDouble();
                double ask = assetNode.get("askPrice").asDouble();

                assetDTOList.add(AssetDTO.builder()
                        .symbolDTO(new SymbolDTO(null, symbol))
                        .exchange(CryptoExchange.BINANCE)
                        .bid(bid)
                        .ask(ask)
                        .build());
            });
        } catch (JsonProcessingException e) {
            //TODO логгер
            e.printStackTrace();
        }
        return assetDTOList;
    }
}
