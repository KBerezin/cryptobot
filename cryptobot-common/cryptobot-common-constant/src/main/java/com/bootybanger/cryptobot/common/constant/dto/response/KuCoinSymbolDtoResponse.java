package com.bootybanger.cryptobot.common.constant.dto.response;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbol;
import lombok.Data;

@Data
public class KuCoinSymbolDtoResponse implements ExchangeSymbol {

    private String symbol;

    @Override
    public String getBaseAsset() {
        //TODO переделать
        String[] assets = symbol.split("_");
        return assets[0];
    }

    @Override
    public String getQuoteAsset() {
        //TODO переделать
        String[] assets = symbol.split("_");
        return assets[1];
    }

    @Override
    public void setBaseAsset(String baseAsset) {

    }

    @Override
    public void setQuoteAsset(String quoteAsset) {

    }
}
