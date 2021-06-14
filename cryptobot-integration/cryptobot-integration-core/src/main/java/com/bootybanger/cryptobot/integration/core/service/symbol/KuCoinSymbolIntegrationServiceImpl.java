package com.bootybanger.cryptobot.integration.core.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.mapper.SymbolDTOMapper;
import com.bootybanger.cryptobot.integration.core.domain.service.symbol.KuCoinSymbolIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.symbol.client.KuCoinSymbolClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KuCoinSymbolIntegrationServiceImpl implements KuCoinSymbolIntegrationService {

    private final KuCoinSymbolClient client;
    private final SymbolDTOMapper mapper;

    @Override
    public Mono<List<SymbolDTO>> getAllSymbols() {
        return client.getKuCoinSymbols().map(mapper::toSymbolDTO);
    }
}
