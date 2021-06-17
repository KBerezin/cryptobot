package com.bootybanger.cryptobot.common.constant.util;

public class PriceMath {
    public static final double REQUIRED_PRICE_DIFFERENCE_PCT = 0.3d;

    public static boolean isPriceDifferenceAppropriate(double priceDifferencePct) {
        return priceDifferencePct >= REQUIRED_PRICE_DIFFERENCE_PCT;
    }

    public static double calculatePriceDifferencePct(double bid, double ask) {
        double diff = bid - ask;
        double relation = diff / bid;
        return relation * 100;
    }

}