package core.service.coin;

import reactor.core.publisher.Mono;

public interface CoinUpdateService {
    Mono<Void> updateCoins();
}
