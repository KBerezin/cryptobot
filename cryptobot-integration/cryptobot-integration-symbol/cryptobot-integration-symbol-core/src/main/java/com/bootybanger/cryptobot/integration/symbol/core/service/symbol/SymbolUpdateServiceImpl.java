package com.bootybanger.cryptobot.integration.symbol.core.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import core.service.catalog.CatalogSymbolIntegrationService;
import core.service.symbol.ExchangeSymbolIntegrationService;
import core.service.symbol.SymbolUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SymbolUpdateServiceImpl implements SymbolUpdateService {

    private final List<ExchangeSymbolIntegrationService> symbolServices;
    private final CatalogSymbolIntegrationService catalogService;

    @Override
    public void updateSymbols() {
        Optional<Mono<List<SymbolDTO>>> monoSymbolListOptional = symbolServices.stream()
                .map(ExchangeSymbolIntegrationService::getAllSymbols)
                .reduce((mono1, mono2) ->
                        mono1.flatMap(symbolList1 -> mono2.map(symbolList2 -> {
                            symbolList2.addAll(symbolList1);
                            return symbolList2;
                        })));
        monoSymbolListOptional.ifPresent(mono -> mono.subscribe(symbolDTOList -> catalogService.addList(symbolDTOList).subscribe()));
    }
}
