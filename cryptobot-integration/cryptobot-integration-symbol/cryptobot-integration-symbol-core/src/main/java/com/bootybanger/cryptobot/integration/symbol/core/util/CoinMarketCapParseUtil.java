package com.bootybanger.cryptobot.integration.symbol.core.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import core.util.AbstractCoinParseUtil;
import org.springframework.stereotype.Component;

@Component
public class CoinMarketCapParseUtil extends AbstractCoinParseUtil {
    public CoinMarketCapParseUtil(ObjectMapper objectMapper) {
        super(objectMapper);
        nodeNameMap.put("listNode", "data");
        nodeNameMap.put("nameNode", "name");
        nodeNameMap.put("symbolNode", "symbol");
        nodeNameMap.put("rankNode", "rank");
        nodeNameMap.put("platformNode", "platform");
    }
}
