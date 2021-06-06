package com.bootybanger.cryptobot.integration.core.domain.mapper.symbol;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;

import java.util.List;

public interface SymbolMapper {
    ExchangeSymbolDTO toExchangeDTO(SymbolDTO symbolDTO);
    List<ExchangeSymbolDTO> toExchangeDTO(List<SymbolDTO> symbolDTO);
    SymbolDTO toSymbolDTO(ExchangeSymbolDTO exchangeSymbolDTO);
    List<SymbolDTO> toSymbolDTO(List<ExchangeSymbolDTO> exchangeSymbolDTO);
}
