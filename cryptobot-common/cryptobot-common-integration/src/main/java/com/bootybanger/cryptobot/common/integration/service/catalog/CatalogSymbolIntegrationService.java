package com.bootybanger.cryptobot.common.integration.service.catalog;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CatalogSymbolIntegrationService {
    Mono<List<SymbolDTO>> getAllSymbols();
    Mono<Void> addList(List<SymbolDTO> symbolDTOList);
}
