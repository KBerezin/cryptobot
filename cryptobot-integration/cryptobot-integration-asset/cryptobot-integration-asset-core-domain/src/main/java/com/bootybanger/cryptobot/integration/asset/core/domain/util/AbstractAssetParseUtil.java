package com.bootybanger.cryptobot.integration.asset.core.domain.util;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractAssetParseUtil implements AssetParseUtil {
    private final ObjectMapper objectMapper;
    protected final Map<String, String> nodeNameMap;

    public AbstractAssetParseUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.nodeNameMap = new HashMap<>();
    }

    @Override
    public List<ExchangeAssetDTO> parseAssetListJson(String json) {
        JsonNode listNode = null;
        try {
            JsonNode tree = objectMapper.readTree(json);
            String listNodeName = nodeNameMap.get("listNode");
            listNode = listNodeName == null ? tree : tree.findValue(listNodeName);
        } catch (JsonProcessingException e) {
            //TODO логгер
            e.printStackTrace();
        }
        return listNode == null ? new CopyOnWriteArrayList<>() : getExchangeAssetDTOListFromNode(listNode);
    }

    @Override
    public List<ExchangeAssetDTO> getExchangeAssetDTOListFromNode(JsonNode assetListNode) {
        List<ExchangeAssetDTO> exchangeAssetDTOList = new CopyOnWriteArrayList<>();
        assetListNode.forEach(assetNode -> {
            ExchangeAssetDTO eaDTO = getExchangeAssetDTOFromNode(assetNode);
            if (eaDTO.getBestBid() != 0 && eaDTO.getBestAsk() != 0) {
                exchangeAssetDTOList.add(eaDTO);
            }
        });
        return exchangeAssetDTOList;
    }

    @Override
    public ExchangeAssetDTO getExchangeAssetDTOFromNode(JsonNode assetNode) {
        String symbol = assetNode.get(nodeNameMap.get("symbolNode"))
                .asText()
                .replaceAll("[-_]", "")
                .toUpperCase();
        double bid = assetNode.get(nodeNameMap.get("bidNode")).asDouble();
        double ask = assetNode.get(nodeNameMap.get("askNode")).asDouble();

        return ExchangeAssetDTO.builder()
                .exchangeSymbolDTO(ExchangeSymbolDTO.builder().symbol(symbol).build())
                .exchange(CryptoExchange.valueOf(nodeNameMap.get("exchangeName")))
                .bestBid(bid)
                .bestAsk(ask)
                .build();
    }

}
