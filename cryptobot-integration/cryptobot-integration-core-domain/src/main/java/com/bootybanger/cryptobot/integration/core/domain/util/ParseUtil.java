package com.bootybanger.cryptobot.integration.core.domain.util;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;

import java.util.List;
import java.util.Map;

public interface ParseUtil {
    List<ExchangeSymbolDTO> parseListExchangeSymbols(String json, Map<String, String> nodeNameMap);

    List<CoinDTO> parseListCoins(String json, Map<String, String> nodeNameMap);
}
