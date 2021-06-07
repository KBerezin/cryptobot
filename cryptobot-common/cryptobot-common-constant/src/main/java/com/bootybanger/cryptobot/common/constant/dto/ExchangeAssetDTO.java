package com.bootybanger.cryptobot.common.constant.dto;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@ToString
public class ExchangeAssetDTO {
    private final ExchangeSymbolDTO exchangeSymbolDTO;
    private final CryptoExchange exchange;
    private final double bid;
    private final double ask;
}
