package com.bootybanger.cryptobot.integration.asset.core.util;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AbstractAssetParseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class KuCoinAssetParseUtil extends AbstractAssetParseUtil {
    public KuCoinAssetParseUtil(ObjectMapper objectMapper) {
        super(objectMapper);
        nodeNameMap.put("listNode", "ticker");
        nodeNameMap.put("symbolNode", "symbol");
        nodeNameMap.put("bidNode", "buy");
        nodeNameMap.put("askNode", "sell");
        nodeNameMap.put("exchangeName", CryptoExchange.KUCOIN.name());
    }
}
