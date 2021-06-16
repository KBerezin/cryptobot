package com.bootybanger.cryptobot.integration.asset.core.util;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AbstractAssetParseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class BinanceAssetParseUtil extends AbstractAssetParseUtil {
    public BinanceAssetParseUtil(ObjectMapper objectMapper) {
        super(objectMapper);
        nodeNameMap.put("listNode", null);
        nodeNameMap.put("symbolNode", "symbol");
        nodeNameMap.put("bidNode", "bidPrice");
        nodeNameMap.put("askNode", "askPrice");
        nodeNameMap.put("exchangeName", CryptoExchange.BINANCE.name());
    }
}
