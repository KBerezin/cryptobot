package com.bootybanger.cryptobot.integration.core.domain.mapper.symbol;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;

import javax.annotation.PostConstruct;
import java.util.List;

public interface SymbolDTOMapper {
    SymbolDTO toSymbolDTO(ExchangeSymbolDTO exchangeSymbolDTO);
    List<SymbolDTO> toSymbolDTO(List<ExchangeSymbolDTO> exchangeSymbolDTO);
    void updateCoins();
}
