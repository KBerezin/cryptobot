package com.bootybanger.cryptobot.common.constant.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(exclude = {"startDateTime", "finishDateTime"})
public class ArbitrageWindowDTO {
    private AssetPair assetPair;
    private LocalDateTime startDateTime;
    private LocalDateTime finishDateTime;
}
