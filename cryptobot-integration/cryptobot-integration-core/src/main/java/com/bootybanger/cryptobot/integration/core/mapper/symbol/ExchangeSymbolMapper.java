package com.bootybanger.cryptobot.integration.core.mapper.symbol;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ExchangeSymbolMapper {

    public ExchangeSymbolDTO toExchangeDTO(SymbolDTO symbolDTO) {
        String symbol = symbolDTO.getName();
        String[] assets = symbol.split("_");
        String baseAsset = assets[0];
        String quoteAsset = assets[1];
        return ExchangeSymbolDTO.builder()
                .symbol(symbol)
                .baseAsset(baseAsset)
                .quoteAsset(quoteAsset)
                .build();
    }

    public List<ExchangeSymbolDTO> toExchangeDTO(List<SymbolDTO> symbolDTOList) {
        return symbolDTOList.stream().map(this::toExchangeDTO).collect(Collectors.toList());
    }

}
