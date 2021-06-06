package com.bootybanger.cryptobot.common.constant.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class ExchangeSymbolDTO {
    private final String symbol;
    private final String baseAsset;
    private final String quoteAsset;
}
