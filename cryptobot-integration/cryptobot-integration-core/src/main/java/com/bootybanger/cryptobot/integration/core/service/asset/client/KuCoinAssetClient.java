package com.bootybanger.cryptobot.integration.core.service.asset.client;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
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
public class KuCoinAssetClient {

    private final KuCoinConfigurationProperties properties;
    private final KuCoinBaseClient client;

    public Mono<List<AssetDTO>> getKuCoinAssets() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                String.class, properties.getAsset().get("getAll"))
                .map(this::parseKuCoinAssetListJson);
    }

    //TODO srp
    private List<AssetDTO> parseKuCoinAssetListJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AssetDTO> assetDTOList = new CopyOnWriteArrayList<>();
        try {
            JsonNode tree = objectMapper.readTree(json);
            JsonNode ticker = tree.findValue("ticker");
            ticker.forEach(assetNode -> {
                String symbol = assetNode.get("symbol").asText().replaceAll("-", "_");
                double bid = assetNode.get("buy").asDouble();
                double ask = assetNode.get("sell").asDouble();

                //TODO можно убрать когда появится исключение символов
                if (bid != 0 && ask != 0) {
                    assetDTOList.add(AssetDTO.builder()
                            .symbolDTO(new SymbolDTO(null, symbol, null))
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
        return assetDTOList;
    }

}
