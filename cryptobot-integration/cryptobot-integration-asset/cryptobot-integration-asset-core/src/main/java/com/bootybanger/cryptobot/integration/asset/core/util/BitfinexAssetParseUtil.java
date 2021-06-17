package com.bootybanger.cryptobot.integration.asset.core.util;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AbstractAssetParseUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class BitfinexAssetParseUtil extends AbstractAssetParseUtil {
    public BitfinexAssetParseUtil(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public List<ExchangeAssetDTO> parseAssetListJson(String json) {
        JsonNode listNode = null;
        try {
            listNode = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
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
        String symbol = assetNode.get(0).asText()
                .substring(1)
                .replaceAll(":", "")
                .replaceAll("UST", "USDT");
        double bid = assetNode.get(1).asDouble();
        double ask = assetNode.get(3).asDouble();

        return ExchangeAssetDTO.builder()
                .exchangeSymbolDTO(ExchangeSymbolDTO.builder().symbol(symbol).build())
                .exchange(CryptoExchange.BITFINEX)
                .bestBid(bid)
                .bestAsk(ask)
                .build();
    }
}
