package com.bootybanger.cryptobot.integration.asset.core.util;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AbstractAssetParseUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class HuobiAssetParseUtil extends AbstractAssetParseUtil {
    public HuobiAssetParseUtil(ObjectMapper objectMapper) {
        super(objectMapper);
        nodeNameMap.put("listNode", "data");
        nodeNameMap.put("symbolNode", "symbol");
        nodeNameMap.put("bidNode", "bid");
        nodeNameMap.put("askNode", "ask");
        nodeNameMap.put("exchangeName", CryptoExchange.HUOBI.name());
    }

    @Override
    public ExchangeAssetDTO getExchangeAssetDTOFromNode(JsonNode assetNode) {
        String symbol = assetNode.get(nodeNameMap.get("symbolNode"))
                .asText()
                .replaceAll("[-_]", "")
                .toUpperCase();
        if (symbol.startsWith("HOT") || symbol.endsWith("HOT")) {
            symbol = symbol.replace("HOT", "HOT2");
        }
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
