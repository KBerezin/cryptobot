package com.bootybanger.cryptobot.integration.core.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.CatalogSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.symbol.client.CatalogSymbolClient;
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
        return client.getAll();
    }

    @Override
    public Mono<Void> addList(List<SymbolDTO> symbolDTOList) {
        return client.addList(symbolDTOList);
    }
}
