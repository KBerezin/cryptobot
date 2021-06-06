package com.bootybanger.cryptobot.common.constant.dto.response;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbol;
import lombok.Data;
import lombok.Setter;

@Data
public class BinanceSymbolDTOResponse implements ExchangeSymbol {
    private String symbol;
    private String baseAsset;
    private String quoteAsset;
}
