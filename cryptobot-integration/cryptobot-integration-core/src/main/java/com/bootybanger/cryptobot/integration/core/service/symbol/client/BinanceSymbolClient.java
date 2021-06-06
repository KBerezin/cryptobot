package com.bootybanger.cryptobot.integration.core.service.symbol.client;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.integration.core.config.BinanceConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.service.BinanceBaseClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BinanceSymbolClient {

    private final BinanceConfigurationProperties properties;
    private final BinanceBaseClient client;

    public Mono<List<ExchangeSymbolDTO>> getBinanceSymbols() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                String.class, properties.getSymbol().get("getAll"))
                .map(this::getExchangeSymbols);

    }

    //TODO srp
    private List<ExchangeSymbolDTO> getExchangeSymbols(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ExchangeSymbolDTO> exchangeSymbolDTOList = new ArrayList<>();
        try {
            JsonNode tree = objectMapper.readTree(json);
            JsonNode symbols = tree.findValue("symbols");
            symbols.forEach(jsonNode -> {
                String symbol = jsonNode.get("symbol").asText();
                String baseAsset = jsonNode.get("baseAsset").asText();
                String quoteAsset = jsonNode.get("quoteAsset").asText();

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
