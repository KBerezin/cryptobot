package com.bootybanger.cryptobot.integration.core.service.asset.client;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;

import com.bootybanger.cryptobot.common.integration.client.KuCoinBaseClient;
import com.bootybanger.cryptobot.integration.symbol.core.config.KuCoinExchangeConfigurationProperties;
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
public class KuCoinAssetClient {

    private final KuCoinExchangeConfigurationProperties properties;
    private final KuCoinBaseClient client;

    public Mono<List<ExchangeAssetDTO>> getKuCoinAssets() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                String.class, properties.getAsset().get("getAll"))
                .map(this::parseKuCoinAssetListJson);
    }

    //TODO srp
    private List<ExchangeAssetDTO> parseKuCoinAssetListJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ExchangeAssetDTO> exchangeAssetDTOList = new CopyOnWriteArrayList<>();
        try {
            JsonNode tree = objectMapper.readTree(json);
            JsonNode ticker = tree.findValue("ticker");
            ticker.forEach(assetNode -> {
                String symbol = assetNode.get("symbol").asText().replaceAll("-", "");
                double bid = assetNode.get("buy").asDouble();
                double ask = assetNode.get("sell").asDouble();

                //TODO можно убрать когда появится исключение символов
                if (bid != 0 && ask != 0) {
                    exchangeAssetDTOList.add(ExchangeAssetDTO.builder()
                            .exchangeSymbolDTO(ExchangeSymbolDTO.builder().symbol(symbol).build())
                            .exchange(CryptoExchange.KUCOIN)
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
