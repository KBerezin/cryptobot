package com.bootybanger.cryptobot.common.constant.dto;

import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(exclude = {"bestBid", "bestAsk", "timeStamp"})
public class AssetDTO {
    private SymbolDTO symbolDTO;
    private long timeStamp;
    private CryptoExchange exchange;
    private List<OrderDTO> bidOrders;
    private List<OrderDTO> askOrders;
    private double bestBid;
    private double bestAsk;
}
