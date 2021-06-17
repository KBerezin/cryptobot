package com.bootybanger.cryptobot.integration.symbol.core.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import core.service.catalog.CatalogSymbolIntegrationService;
import core.service.symbol.ExchangeSymbolIntegrationService;
import core.service.symbol.SymbolUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SymbolUpdateServiceImpl implements SymbolUpdateService {

    private final List<ExchangeSymbolIntegrationService> symbolServices;
    private final CatalogSymbolIntegrationService catalogService;

    @Override
    public Flux<Void> updateSymbols() {
        Stream<Mono<List<SymbolDTO>>> monoStream = symbolServices.stream()
                .map(ExchangeSymbolIntegrationService::getAllSymbols);
        return Flux.fromStream(monoStream).flatMap(listMono -> listMono.flatMap(catalogService::addList));
    }
}
