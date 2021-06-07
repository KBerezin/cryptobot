package com.bootybanger.cryptobot.integration.core.service.symbol.client;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.integration.core.config.KuCoinConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.service.KuCoinBaseClient;
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
public class KuCoinSymbolClient {

    private final KuCoinConfigurationProperties properties;
    private final KuCoinBaseClient client;

    public Mono<List<ExchangeSymbolDTO>> getKuCoinSymbols() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(), String.class,
                properties.getSymbol().get("getAll"))
                .map(this::parseKuCoinExchangeSymbolListJson);
    }

    //TODO srp
    private List<ExchangeSymbolDTO> parseKuCoinExchangeSymbolListJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ExchangeSymbolDTO> exchangeSymbolDTOList = new CopyOnWriteArrayList<>();
        try {
            JsonNode tree = objectMapper.readTree(json);
            JsonNode ticker = tree.findValue("ticker");
            ticker.forEach(jsonNode -> {
                String symbol = jsonNode.get("symbol").asText();
                String[] assets = symbol.split("-");
                String baseAsset = assets[0];
                String quoteAsset = assets[1];
                ExchangeSymbolDTO es = ExchangeSymbolDTO.builder()
                        .symbol(symbol)
                        .baseAsset(baseAsset)
                        .quoteAsset(quoteAsset)
                        .build();
                exchangeSymbolDTOList.add(es);
            });
        } catch (JsonProcessingException e) {
            //TODO логгер
            e.printStackTrace();
        }
        return exchangeSymbolDTOList;
    }
}
