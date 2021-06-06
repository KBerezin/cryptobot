package com.bootybanger.cryptobot.common.constant.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(exclude = "id")
public class SymbolDTO {
    private UUID id;
    private String name;
}
