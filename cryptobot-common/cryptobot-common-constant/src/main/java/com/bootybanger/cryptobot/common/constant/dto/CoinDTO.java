package com.bootybanger.cryptobot.common.constant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CoinDTO {
    private UUID id;
    private String name;
    private String symbol;
    private Integer rank;
    private String platform;
}
