package com.bootybanger.cryptobot.common.constant.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@EqualsAndHashCode(exclude = "id")
@ToString
public class SymbolDTO {
    private final UUID id;
    private final String name;
}
