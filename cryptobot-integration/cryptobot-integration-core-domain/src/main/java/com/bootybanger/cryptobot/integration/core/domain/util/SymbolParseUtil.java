package com.bootybanger.cryptobot.integration.core.domain.util;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;

import java.util.List;

public interface SymbolParseUtil {
    List<ExchangeSymbolDTO> parseListExchangeSymbols(String json);
}
