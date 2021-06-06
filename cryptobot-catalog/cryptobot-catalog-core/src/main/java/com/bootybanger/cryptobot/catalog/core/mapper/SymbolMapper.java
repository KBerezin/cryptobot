package com.bootybanger.cryptobot.catalog.core.mapper;

import com.bootybanger.cryptobot.catalog.core.entity.Symbol;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SymbolMapper extends CommonEntityDtoMapper<SymbolDTO, Symbol> {
}
