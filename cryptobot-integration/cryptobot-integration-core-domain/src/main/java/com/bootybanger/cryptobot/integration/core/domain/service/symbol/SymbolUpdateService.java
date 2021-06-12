package com.bootybanger.cryptobot.integration.core.domain.service.symbol;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;

import java.util.List;

public interface SymbolUpdateService {
    void updateSymbols();
    List<SymbolDTO> getAllSymbols();
}
