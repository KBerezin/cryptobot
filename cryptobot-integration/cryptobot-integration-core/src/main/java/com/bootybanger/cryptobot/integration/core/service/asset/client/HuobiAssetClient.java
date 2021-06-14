package com.bootybanger.cryptobot.integration.core.service.asset.client;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.common.integration.client.HuobiBaseClient;
import com.bootybanger.cryptobot.integration.symbol.core.config.HuobiExchangeConfigurationProperties;
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
public class HuobiAssetClient {

    private final HuobiExchangeConfigurationProperties properties;
    private final HuobiBaseClient client;

    public Mono<List<ExchangeAssetDTO>> getHuobiAssets() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                String.class, properties.getAsset().get("getAll"))
                .map(this::parseHuobiAssetListJson);
    }

    //TODO srp
    private List<ExchangeAssetDTO> parseHuobiAssetListJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ExchangeAssetDTO> exchangeAssetDTOList = new CopyOnWriteArrayList<>();
        try {
            JsonNode tree = objectMapper.readTree(json);
            JsonNode data = tree.findValue("data");
            data.forEach(assetNode -> {
                String symbol = assetNode.get("symbol").asText().toUpperCase();
                double bid = assetNode.get("bid").asDouble();
                double ask = assetNode.get("ask").asDouble();

                //TODO можно убрать когда появится исключение символов
                exchangeAssetDTOList.add(ExchangeAssetDTO.builder()
                        .exchangeSymbolDTO(ExchangeSymbolDTO.builder().symbol(symbol).build())
                        .exchange(CryptoExchange.HUOBI)
                        .bestBid(bid)
                        .bestAsk(ask)
                        .build());

            });
        } catch (JsonProcessingException e) {
            //TODO логгер
            e.printStackTrace();
        }
        return exchangeAssetDTOList;
    }
}
