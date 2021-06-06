package com.bootybanger.cryptobot.integration.core.mapper;


import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbol;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.response.BinanceSymbolDTOResponse;
import com.bootybanger.cryptobot.integration.core.domain.mapper.symbol.BinanceSymbolConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BinanceSymbolConverterImpl implements BinanceSymbolConverter {

    @Override
    public SymbolDTO toSymbolDTO(ExchangeSymbol exchangeSymbol) {
        SymbolDTO symbolDTO = new SymbolDTO();
        symbolDTO.setName(exchangeSymbol.getBaseAsset() + "_" + exchangeSymbol.getQuoteAsset());
        return symbolDTO;
    }

    @Override
    public List<SymbolDTO> toSymbolDTO(List<ExchangeSymbol> exchangeSymbolList) {
        return exchangeSymbolList.stream().map(this::toSymbolDTO).collect(Collectors.toList());
    }

    @Override
    public ExchangeSymbol toExchangeSymbol(SymbolDTO symbolDTO) {
        //TODO добавить валидаций
        String[] assets = symbolDTO.getName().split("_");
        BinanceSymbolDTOResponse binanceExchangeSymbol = new BinanceSymbolDTOResponse();
        binanceExchangeSymbol.setSymbol(assets[0] + assets[1]);
        binanceExchangeSymbol.setBaseAsset(assets[0]);
        binanceExchangeSymbol.setQuoteAsset(assets[1]);
        return binanceExchangeSymbol;
    }

    @Override
    public List<ExchangeSymbol> toExchangeSymbol(List<SymbolDTO> symbolDTOList) {
        return symbolDTOList.stream().map(this::toExchangeSymbol).collect(Collectors.toList());
    }
}
