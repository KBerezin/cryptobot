package com.bootybanger.cryptobot.integration.core.mapper.symbol;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.mapper.symbol.SymbolDTOMapper;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CatalogCoinIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * returns empty SymbolDTO or empty List if appropriate coins did not find
 */

@Component
@RequiredArgsConstructor
public class SymbolDTOMapperImpl implements SymbolDTOMapper {

    private List<CoinDTO> allCoins = new ArrayList<>();
    private final CatalogCoinIntegrationService catalogCoinIntegrationService;

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
            System.out.println(symbol);
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

    @Override
    public void updateCoins() {
        allCoins = catalogCoinIntegrationService.getList().block();
    }

}
