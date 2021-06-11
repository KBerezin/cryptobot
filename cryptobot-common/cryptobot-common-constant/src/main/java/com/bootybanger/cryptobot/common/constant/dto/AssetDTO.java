package com.bootybanger.cryptobot.common.constant.dto;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * bid - это то, за сколько ты можешь продать.
 * ask - это то за сколько ты можешь купить
 *
 * нас интересует разница: bid минус ask > 2%
 */


@Data
@Builder
@EqualsAndHashCode(exclude = {"bid", "ask"})
public class AssetDTO {
    private SymbolDTO symbolDTO;
    private CryptoExchange exchange;
    private double bid;
    private double ask;
}
