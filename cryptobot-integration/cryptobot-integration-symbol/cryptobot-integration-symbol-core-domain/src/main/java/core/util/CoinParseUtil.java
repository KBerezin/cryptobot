package core.util;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;

import java.util.List;

public interface CoinParseUtil {
    List<CoinDTO> parseListCoins(String json);
}
