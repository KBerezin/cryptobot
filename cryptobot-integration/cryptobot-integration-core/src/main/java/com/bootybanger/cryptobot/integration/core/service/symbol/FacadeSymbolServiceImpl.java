package com.bootybanger.cryptobot.integration.core.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.BinanceSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CatalogSymbolIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

public class FacadeSymbolServiceImpl {

    @Autowired
    BinanceSymbolIntegrationService service;

    @Autowired
    CatalogSymbolIntegrationService catalogSymbolIntegrationService;

    public void updateSymbols() {
        Mono<List<SymbolDTO>> binanceSymbols = service.getAllSymbols();
        Mono<List<SymbolDTO>> catalogSymbols = catalogSymbolIntegrationService.getAllSymbols();

        Mono<List<String>> catalogSymbolNames = catalogSymbols.map(symbolDTOList ->
                symbolDTOList.stream()
                        .map(SymbolDTO::getName)
                        .collect(Collectors.toList()));

        Mono<List<SymbolDTO>> exchangeSymbols = catalogSymbolNames
                .flatMap(names -> binanceSymbols.map(symbolDTOList -> symbolDTOList.stream()
                        .filter(symbolDTO -> !names.contains(symbolDTO.getName()))
                        .collect(Collectors.toList())));

        exchangeSymbols.subscribe(symbolDTOList -> catalogSymbolIntegrationService.addList(symbolDTOList).subscribe());
    }
}
