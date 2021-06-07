package com.bootybanger.cryptobot.common.constant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

//TODO
/**
 * EqualsAndHashCode уже зайдествован, не менять.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class SymbolDTO {
    private UUID id;
    private String name;
}
