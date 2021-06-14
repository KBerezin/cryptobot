package com.bootybanger.cryptobot.integration.core.domain.util;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;

import java.util.List;

public interface CoinParseUtil {
    List<CoinDTO> parseListCoins(String json);
}
