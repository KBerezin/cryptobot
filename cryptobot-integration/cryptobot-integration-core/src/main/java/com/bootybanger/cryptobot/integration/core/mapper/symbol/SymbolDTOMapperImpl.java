package com.bootybanger.cryptobot.integration.core.mapper.symbol;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.common.constant.mapper.SymbolDTOMapper;
import core.service.catalog.CatalogSymbolIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * returns empty SymbolDTO or empty List if appropriate coins did not find
 */

@Component
@RequiredArgsConstructor
public class SymbolDTOMapperImpl implements SymbolDTOMapper {

    private List<SymbolDTO> allSymbolDto;
    private final CatalogSymbolIntegrationService catalogSymbolIntegrationService;

    @Override
    public SymbolDTO toSymbolDTO(ExchangeSymbolDTO exchangeSymbolDTO) {
        Optional<SymbolDTO> symbolDTOOptional = allSymbolDto.stream()
                .filter(symbolDTO -> {
                    String symbolName = symbolDTO.getName().replaceAll("_", "");
                    return symbolName.equals(exchangeSymbolDTO.getSymbol());
                })
                .findAny();
        return symbolDTOOptional.orElseGet(SymbolDTO::new);
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
    @Scheduled(cron = "* * * * * *")
    public void update() {
        allSymbolDto = catalogSymbolIntegrationService.getAllSymbols().block();
    }
}
