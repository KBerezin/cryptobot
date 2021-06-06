package com.bootybanger.cryptobot.common.constant.dto;

public interface ExchangeSymbol {
    String getSymbol();
    String getBaseAsset();
    String getQuoteAsset();
    void setSymbol(String symbol);
    void setBaseAsset(String baseAsset);
    void setQuoteAsset(String quoteAsset);
}
