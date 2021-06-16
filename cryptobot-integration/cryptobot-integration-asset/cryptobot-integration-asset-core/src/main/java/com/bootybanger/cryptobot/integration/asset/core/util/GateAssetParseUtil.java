package com.bootybanger.cryptobot.integration.asset.core.util;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.integration.asset.core.domain.util.AbstractAssetParseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class GateAssetParseUtil extends AbstractAssetParseUtil {
    public GateAssetParseUtil(ObjectMapper objectMapper) {
        super(objectMapper);
        nodeNameMap.put("listNode", null);
        nodeNameMap.put("symbolNode", "currency_pair");
        nodeNameMap.put("bidNode", "highest_bid");
        nodeNameMap.put("askNode", "lowest_ask");
        nodeNameMap.put("exchangeName", CryptoExchange.GATE.name());
    }
}
