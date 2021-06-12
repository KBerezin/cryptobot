package com.bootybanger.cryptobot.integration.core.util;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class ParseUtil {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ExchangeSymbolDTO> parseListExchangeSymbols(String json, Map<String, String> nodeNameMap) {
        List<ExchangeSymbolDTO> exchangeSymbolDTOList = new ArrayList<>();
        try {
            JsonNode tree = objectMapper.readTree(json);
            JsonNode data = tree.findValue(nodeNameMap.get("listNode"));
            data.forEach(jsonNode -> {
                ExchangeSymbolDTO exchangeSymbolDTO = getExchangeSymbolFromJsonNode(jsonNode, nodeNameMap);
                exchangeSymbolDTOList.add(exchangeSymbolDTO);
            });
        } catch (JsonProcessingException e) {
            //TODO логгер
            e.printStackTrace();
        }
        return exchangeSymbolDTOList;
    }

    private ExchangeSymbolDTO getExchangeSymbolFromJsonNode(JsonNode jsonNode, Map<String, String> nodeNameMap) {
        String symbol = jsonNode.get(nodeNameMap.get("symbol")).asText("");
        return ExchangeSymbolDTO.builder().symbol(symbol).build();
    }

    public List<CoinDTO> parseListCoins(String json, Map<String, String> nodeNameMap) {
        List<CoinDTO> coinDTOList = new ArrayList<>();
        try {
            JsonNode tree = objectMapper.readTree(json);
            JsonNode data = tree.findValue(nodeNameMap.get("listNode"));
            data.forEach(jsonNode -> {
                CoinDTO coinDTO = getCoinFromJsonNode(jsonNode, nodeNameMap);
                coinDTOList.add(coinDTO);
            });
        } catch (JsonProcessingException e) {
            //TODO логгер
            e.printStackTrace();
        }
        return coinDTOList;
    }

    public CoinDTO getCoinFromJsonNode(JsonNode coinNode, Map<String, String> nodeNameMap) {
        String name = coinNode.get(nodeNameMap.get("nameNode")).asText("");
        String symbol = coinNode.get(nodeNameMap.get("symbolNode")).asText("");
        Integer rank = coinNode.get(nodeNameMap.get("rankNode")).asInt(-1);
        String platform = getNodeFieldAsString(coinNode.get(nodeNameMap.get("platformNode")), "name");
        return CoinDTO.builder().name(name).symbol(symbol).rank(rank).platform(platform)
                .build();
    }

    public String getNodeFieldAsString(JsonNode jsonNode, String fieldName) {
        if(jsonNode == null || jsonNode.get(fieldName) == null) {
            return "";
        }
        return jsonNode.get(fieldName).asText();
    }
}
