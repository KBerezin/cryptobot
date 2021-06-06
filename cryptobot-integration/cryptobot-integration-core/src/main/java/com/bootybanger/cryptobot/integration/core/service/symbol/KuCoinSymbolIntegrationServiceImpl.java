package com.bootybanger.cryptobot.integration.core.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.integration.core.domain.mapper.symbol.BinanceSymbolConverter;
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
    //TODO сделать свой
    private final BinanceSymbolConverter converter;

    @Override
    public Mono<List<SymbolDTO>> getAllSymbols() {
        return client.getKuCoinSymbols().map(converter::toSymbolDTO);
    }
}
