package com.bootybanger.cryptobot.integration.core.mapper.symbol;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.mapper.symbol.SymbolDTOMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SymbolDTOMapperImpl implements SymbolDTOMapper {

    @Override
    public SymbolDTO toSymbolDTO(ExchangeSymbolDTO exchangeSymbolDTO) {
        SymbolDTO symbolDTO = new SymbolDTO();
        symbolDTO.setName(exchangeSymbolDTO.getBaseAsset() + "_" + exchangeSymbolDTO.getQuoteAsset());
        return symbolDTO;
    }

    @Override
    public List<SymbolDTO> toSymbolDTO(List<ExchangeSymbolDTO> exchangeSymbolDTOList) {
        return exchangeSymbolDTOList.stream().map(this::toSymbolDTO).collect(Collectors.toList());
    }
}
