package com.bootybanger.cryptobot.integration.symbol.core.util;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.util.AbstractSymbolParseUtil;
import org.springframework.stereotype.Component;

@Component
public class BinanceSymbolParseUtilImpl extends AbstractSymbolParseUtil {
    public BinanceSymbolParseUtilImpl(ObjectMapper objectMapper) {
        super(objectMapper);
        nodeNameMap.put("exchangeName", CryptoExchange.BINANCE.name());
        nodeNameMap.put("listNode", "symbols");
        nodeNameMap.put("symbolNode", "symbol");
    }
}
