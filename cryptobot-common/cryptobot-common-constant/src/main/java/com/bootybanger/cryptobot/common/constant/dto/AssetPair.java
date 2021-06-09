package com.bootybanger.cryptobot.common.constant.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AssetPair {
    private AssetDTO ask;
    private AssetDTO bid;
}
