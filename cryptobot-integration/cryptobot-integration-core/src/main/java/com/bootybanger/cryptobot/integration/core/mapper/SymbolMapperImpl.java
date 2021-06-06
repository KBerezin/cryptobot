package com.bootybanger.cryptobot.integration.core.mapper;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.mapper.symbol.SymbolMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SymbolMapperImpl implements SymbolMapper {

    @Override
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

    @Override
    public List<ExchangeSymbolDTO> toExchangeDTO(List<SymbolDTO> symbolDTOList) {
        return symbolDTOList.stream().map(this::toExchangeDTO).collect(Collectors.toList());
    }

    @Override
    public SymbolDTO toSymbolDTO(ExchangeSymbolDTO exchangeSymbolDTO) {
        return SymbolDTO.builder()
                .name(exchangeSymbolDTO.getBaseAsset() + "_" + exchangeSymbolDTO.getQuoteAsset())
                .build();
    }

    @Override
    public List<SymbolDTO> toSymbolDTO(List<ExchangeSymbolDTO> exchangeSymbolDTOList) {
        return exchangeSymbolDTOList.stream().map(this::toSymbolDTO).collect(Collectors.toList());
    }
}
