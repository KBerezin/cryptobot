package core.service.coin;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CoinMarketCapCoinIntegrationService {
    Mono<List<CoinDTO>> getAllCoins();
}
