package com.bootybanger.cryptobot.integration.core.util;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.core.domain.util.AbstractSymbolParseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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
