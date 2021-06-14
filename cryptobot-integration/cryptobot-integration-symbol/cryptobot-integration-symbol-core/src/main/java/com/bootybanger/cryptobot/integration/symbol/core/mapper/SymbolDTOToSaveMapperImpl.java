package com.bootybanger.cryptobot.integration.symbol.core.mapper;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.common.constant.mapper.SymbolDTOMapper;
import core.service.catalog.CatalogCoinIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * returns empty SymbolDTO or empty List if appropriate coins did not find
 */

@Component
@RequiredArgsConstructor
public class SymbolDTOToSaveMapperImpl implements SymbolDTOMapper {

    private final CatalogCoinIntegrationService catalogCoinIntegrationService;
    private List<CoinDTO> allCoins;

    @Override
    public SymbolDTO toSymbolDTO(ExchangeSymbolDTO exchangeSymbolDTO) {
        String symbol = exchangeSymbolDTO.getSymbol();
        List<CoinDTO> baseCoins = allCoins.stream()
                .filter(baseCoin -> symbol.startsWith(baseCoin.getSymbol()))
                .filter(baseCoin -> {
                    List<CoinDTO> quoteCoins = allCoins.stream()
                            .filter(quoteCoin -> symbol.equals(baseCoin.getSymbol() + quoteCoin.getSymbol()))
                            .collect(Collectors.toList());
                    if (quoteCoins.size() > 1) {
                        throw new RuntimeException("Too many appropriate quote coins: symbol:" + symbol +
                                " quote " + quoteCoins.toString() +  " basecoin: " + baseCoin.toString());
                    }
                    return quoteCoins.size() == 1;
                }).collect(Collectors.toList());
        if (baseCoins.size() == 0) {
            System.out.println("Unrecognized symbol: " + symbol);
            return new SymbolDTO();
        }
        CoinDTO baseAsset = baseCoins.get(0);
        String name = symbol.replaceAll(baseAsset.getSymbol(), baseAsset.getSymbol() + "_");
        SymbolDTO symbolDTO = new SymbolDTO();
        symbolDTO.setName(name);
        symbolDTO.setBaseAsset(baseAsset);
        return symbolDTO;
    }

    @Override
    public List<SymbolDTO> toSymbolDTO(List<ExchangeSymbolDTO> exchangeSymbolDTOList) {
        return exchangeSymbolDTOList.stream()
                .map(this::toSymbolDTO)
                .filter(symbolDTO -> symbolDTO.getName() != null)
                .collect(Collectors.toList());
    }

    //TODO
    @Override
    @PostConstruct
    public void update() {
        allCoins = catalogCoinIntegrationService.getList().block();
    }
}
