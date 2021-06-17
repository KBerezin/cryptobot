package core.service.symbol;

import reactor.core.publisher.Flux;

public interface SymbolUpdateService {
    Flux<Void> updateSymbols();
}
