package com.bootybanger.cryptobot.integration.core.service.symbol.client;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbol;
import com.bootybanger.cryptobot.common.constant.dto.response.BinanceSymbolDTOResponseContainer;
import com.bootybanger.cryptobot.integration.core.config.BinanceConfigurationProperties;
import com.bootybanger.cryptobot.integration.core.service.BinanceBaseClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BinanceSymbolClient {

    private final BinanceConfigurationProperties properties;
    private final BinanceBaseClient client;

    public Mono<List<ExchangeSymbol>> getBinanceSymbols() {
        return client.getClient(properties.getBaseUrl(), new HashMap<>(), new HashMap<>(),
                BinanceSymbolDTOResponseContainer.class, properties.getSymbol().get("getAll"))
                .map(BinanceSymbolDTOResponseContainer::getSymbols)
                .map(e -> e.stream()
                        .map(ExchangeSymbol.class::cast)
                        .collect(Collectors.toList()));
    }

}
