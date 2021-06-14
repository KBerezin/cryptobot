package com.bootybanger.cryptobot.integration.core.domain.util;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSymbolParseUtil implements SymbolParseUtil {
    private final ObjectMapper objectMapper;
    protected final Map<String, String> nodeNameMap;

    public AbstractSymbolParseUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.nodeNameMap = new HashMap<>();
    }

    @Override
    public List<ExchangeSymbolDTO> parseListExchangeSymbols(String json) {
        List<ExchangeSymbolDTO> exchangeSymbolDTOList = new ArrayList<>();
        try {
            JsonNode tree = objectMapper.readTree(json);
            JsonNode data = tree.findValue(nodeNameMap.get("listNode"));
            data.forEach(jsonNode -> {
                ExchangeSymbolDTO exchangeSymbolDTO = getExchangeSymbolFromJsonNode(jsonNode);
                exchangeSymbolDTOList.add(exchangeSymbolDTO);
            });
        } catch (JsonProcessingException e) {
            //TODO логгер
            e.printStackTrace();
        }
        return exchangeSymbolDTOList;
    }

    private ExchangeSymbolDTO getExchangeSymbolFromJsonNode(JsonNode jsonNode) {
        String symbolName = jsonNode.get(nodeNameMap.get("symbolNode")).asText("");
        String symbol = handleSymbolName(symbolName, nodeNameMap.get("exchangeName"));
        return ExchangeSymbolDTO.builder().symbol(symbol).build();
    }

    private String handleSymbolName(String symbolName, String exchangeName) {
        CryptoExchange cryptoExchange = CryptoExchange.valueOf(exchangeName);
        switch (cryptoExchange) {
            case KUCOIN:
                return symbolName.replace("-", "");
            default:
                return symbolName;
        }
    }
}
