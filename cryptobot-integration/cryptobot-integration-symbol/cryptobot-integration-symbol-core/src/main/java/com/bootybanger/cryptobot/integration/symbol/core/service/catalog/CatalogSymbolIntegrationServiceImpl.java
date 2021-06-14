package com.bootybanger.cryptobot.integration.symbol.core.service.catalog;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.symbol.core.service.client.CatalogSymbolClient;
import core.service.catalog.CatalogSymbolIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogSymbolIntegrationServiceImpl implements CatalogSymbolIntegrationService {

    private final CatalogSymbolClient client;

    @Override
    public Mono<List<SymbolDTO>> getAllSymbols() {
        return client.getAllSymbols();
    }

    @Override
    public Mono<Void> addList(List<SymbolDTO> symbolDTOList) {
        return client.addSymbolList(symbolDTOList);
    }
}
