package com.bootybanger.cryptobot.catalog.core.service;

import com.bootybanger.cryptobot.catalog.core.domain.service.SymbolService;
import com.bootybanger.cryptobot.catalog.core.entity.Symbol;
import com.bootybanger.cryptobot.catalog.core.mapper.SymbolMapper;
import com.bootybanger.cryptobot.catalog.core.repository.SymbolRepository;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SymbolServiceImpl implements SymbolService {

    private final SymbolRepository symbolRepository;
    private final SymbolMapper symbolMapper;

    @Override
    public Optional<SymbolDTO> getById(UUID symbolId) {
        Optional<Symbol> symbolOptional = symbolRepository.findById(symbolId);
        return symbolOptional.map(symbolMapper::toDto);
    }

    @Override
    public Optional<SymbolDTO> getByName(String symbolName) {
        Optional<Symbol> symbolOptional = symbolRepository.findByName(symbolName);
        return symbolOptional.map(symbolMapper::toDto);
    }

    @Override
    public List<SymbolDTO> getAll() {
        List<Symbol> symbolList = symbolRepository.findAll();
        return symbolMapper.toDto(symbolList);
    }

    @Override
    public void add(SymbolDTO symbolDTO) {
        Symbol symbol = symbolMapper.toEntity(symbolDTO);
        symbolRepository.save(symbol);
    }

    @Override
    public void addAll(List<SymbolDTO> symbolDTOList) {
        List<Symbol> symbolList = symbolDTOList.stream()
                .distinct()
                .sorted(Comparator.comparing(SymbolDTO::getName))
                .map(symbolMapper::toEntity)
                .collect(Collectors.toList());
        symbolList.forEach(s -> {
            if (!symbolRepository.existsByName(s.getName())) {
                symbolRepository.save(s);
            }
        });
    }

    @Override
    public void removeById(UUID symbolId) {
        symbolRepository.deleteById(symbolId);
    }
}
