package com.bootybanger.cryptobot.integration.asset.core.util;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AbstractAssetParseUtil;
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
}
