package com.bootybanger.cryptobot.common.constant.dto;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AssetPair {
    private SymbolDTO symbolDTO;
    private CryptoExchange askExchange;
    private CryptoExchange bidExchange;
    private double ask;
    private double bid;
}
