package com.bootybanger.cryptobot.catalog.core.domain.service;

import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SymbolService {
    Optional<SymbolDTO> getById(UUID symbolId);

    Optional<SymbolDTO> getByName(String symbolName);

    List<SymbolDTO> getAll();

    void add(SymbolDTO symbolDTO);

    void removeById(UUID symbolId);

    void addAll(List<SymbolDTO> symbolList);

}
