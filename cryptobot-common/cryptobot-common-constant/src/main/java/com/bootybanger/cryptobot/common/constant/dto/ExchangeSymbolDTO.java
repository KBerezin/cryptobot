package com.bootybanger.cryptobot.common.constant.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@ToString
public class ExchangeSymbolDTO {
    private final String symbol;
}
