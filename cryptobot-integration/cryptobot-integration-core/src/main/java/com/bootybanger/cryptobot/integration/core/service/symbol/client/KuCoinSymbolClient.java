package com.bootybanger.cryptobot.integration.core.service.symbol.client;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbol;
import com.bootybanger.cryptobot.common.constant.dto.response.KuCoinSymbolDTOContainer;
import com.bootybanger.cryptobot.integration.core.config.KuCoinConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.service.KuCoinBaseClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KuCoinSymbolClient {

    private final KuCoinConfigurationProperties properties;
    private final KuCoinBaseClient client;

    public Mono<List<ExchangeSymbol>> getKuCoinSymbols() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(), String.class,
                properties.getSymbol().get("getAll"))
                .map(s -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        JsonNode tree = objectMapper.readTree(s);
                        JsonNode ticker = tree.findValue("ticker");
                        ticker.forEach(jsonNode -> System.out.println(jsonNode.get("symbol").asText()));

                    } catch (JsonProcessingException e) {
                        //TODO логгер
                        e.printStackTrace();
                    }
                    return Arrays.asList(s);
                })
                .map(e -> e.stream()
                        .map(ExchangeSymbol.class::cast)
                        .collect(Collectors.toList()));
    }
}
