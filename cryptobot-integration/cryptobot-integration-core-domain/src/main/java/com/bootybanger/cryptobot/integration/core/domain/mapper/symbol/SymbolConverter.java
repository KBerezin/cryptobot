package com.bootybanger.cryptobot.integration.core.domain.mapper.symbol;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbol;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;


import java.util.List;

public interface SymbolConverter {
    SymbolDTO toSymbolDTO(ExchangeSymbol exchangeSymbol);
    List<SymbolDTO> toSymbolDTO(List<ExchangeSymbol> exchangeSymbolList);
    ExchangeSymbol toExchangeSymbol(SymbolDTO symbolDTO);
    List<ExchangeSymbol> toExchangeSymbol(List<SymbolDTO> symbolDTOList);
}
