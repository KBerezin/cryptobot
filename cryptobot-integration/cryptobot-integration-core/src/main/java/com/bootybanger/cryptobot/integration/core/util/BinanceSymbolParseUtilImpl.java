package com.bootybanger.cryptobot.integration.core.util;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.core.domain.util.AbstractSymbolParseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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
