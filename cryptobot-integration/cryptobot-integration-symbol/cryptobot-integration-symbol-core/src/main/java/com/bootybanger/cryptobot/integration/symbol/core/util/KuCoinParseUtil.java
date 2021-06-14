package com.bootybanger.cryptobot.integration.symbol.core.util;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.util.AbstractSymbolParseUtil;
import org.springframework.stereotype.Component;

@Component
public class KuCoinParseUtil extends AbstractSymbolParseUtil {
    public KuCoinParseUtil(ObjectMapper objectMapper) {
        super(objectMapper);
        nodeNameMap.put("exchangeName", CryptoExchange.KUCOIN.name());
        nodeNameMap.put("listNode", "ticker");
        nodeNameMap.put("symbolNode", "symbol");
    }
}
