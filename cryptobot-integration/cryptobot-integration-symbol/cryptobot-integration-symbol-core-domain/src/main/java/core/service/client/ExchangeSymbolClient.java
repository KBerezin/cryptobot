package core.service.client;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExchangeSymbolClient {
    Mono<List<ExchangeSymbolDTO>> getExchangeSymbols();
}
