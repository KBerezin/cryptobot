package com.bootybanger.cryptobot.common.constant.mapper;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SymbolDTOMapper {
    SymbolDTO toSymbolDTO(ExchangeSymbolDTO exchangeSymbolDTO);
    List<SymbolDTO> toSymbolDTO(List<ExchangeSymbolDTO> exchangeSymbolDTO);
    void update();
}
