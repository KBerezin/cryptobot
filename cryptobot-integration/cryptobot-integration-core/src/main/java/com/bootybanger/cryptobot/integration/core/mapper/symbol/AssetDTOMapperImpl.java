package com.bootybanger.cryptobot.integration.core.mapper.symbol;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.bootybanger.cryptobot.common.constant.mapper.AssetDTOMapper;
import com.bootybanger.cryptobot.common.constant.mapper.SymbolDTOMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssetDTOMapperImpl implements AssetDTOMapper {

    private final SymbolDTOMapper mapper;

    public AssetDTOMapperImpl(@Qualifier("symbolDTOMapperImpl") SymbolDTOMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public AssetDTO toAssetDTO(ExchangeAssetDTO exchangeAssetDTO) {
        SymbolDTO symbolDTO = mapper.toSymbolDTO(exchangeAssetDTO.getExchangeSymbolDTO());
        double bestAsk = exchangeAssetDTO.getBestAsk();
        double bestBid = exchangeAssetDTO.getBestBid();
        CryptoExchange exchange = exchangeAssetDTO.getExchange();
        return AssetDTO.builder()
                .symbolDTO(symbolDTO)
                .bestAsk(bestAsk)
                .bestBid(bestBid)
                .exchange(exchange)
                .build();
    }

    @Override
    public List<AssetDTO> toAssetDTO(List<ExchangeAssetDTO> exchangeAssetDTOList) {
        return exchangeAssetDTOList.stream()
                .map(this::toAssetDTO)
                .filter(assetDTO -> assetDTO.getSymbolDTO().getName() != null)
                .collect(Collectors.toList());
    }
}
