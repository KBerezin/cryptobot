package core.util;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;

import java.util.List;

public interface SymbolParseUtil {
    List<ExchangeSymbolDTO> parseListExchangeSymbols(String json);
}
