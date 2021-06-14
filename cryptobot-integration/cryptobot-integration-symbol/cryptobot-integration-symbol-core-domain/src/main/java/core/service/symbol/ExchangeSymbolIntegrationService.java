package core.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExchangeSymbolIntegrationService {
    Mono<List<SymbolDTO>> getAllSymbols();
}
