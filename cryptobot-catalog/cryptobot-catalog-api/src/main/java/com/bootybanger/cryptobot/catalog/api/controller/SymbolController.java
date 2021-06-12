package com.bootybanger.cryptobot.catalog.api.controller;

import com.bootybanger.cryptobot.catalog.core.domain.service.SymbolService;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/catalog/symbol")
public class SymbolController {
    private final SymbolService symbolService;

    @GetMapping("/get/{symbolId}")
    public ResponseEntity<SymbolDTO> getSymbolById(@PathVariable UUID symbolId) {
        Optional<SymbolDTO> symbolDTOOptional = symbolService.getById(symbolId);
        return symbolDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/get/{symbolName}")
    public ResponseEntity<SymbolDTO> getSymbolById(@PathVariable String symbolName) {
        Optional<SymbolDTO> symbolDTOOptional = symbolService.getByName(symbolName);
        return symbolDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/get/list/{baseAsset}")
    public ResponseEntity<List<SymbolDTO>> getSymbolsByBaseAsset(@PathVariable String baseAsset) {
        //TODO
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/list/{quoteAsset}")
    public ResponseEntity<List<SymbolDTO>> getSymbolsByQuoteAsset(@PathVariable String quoteAsset) {
        //TODO
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/list/all")
    public ResponseEntity<List<SymbolDTO>> getAllSymbols() {
        List<SymbolDTO> allSymbols = symbolService.getAll();
        return ResponseEntity.ok(allSymbols);
    }

    @PostMapping("/add/single")
    public ResponseEntity<SymbolDTO> addSymbol(@RequestBody SymbolDTO symbolDto) {
        symbolService.add(symbolDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/list")
    public ResponseEntity<Void> addSymbols(@RequestBody List<SymbolDTO> symbols) {
        if (!symbols.isEmpty()) {
            symbolService.addAll(symbols);
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{symbolId}")
    public ResponseEntity<Void> removeSymbolById(@PathVariable UUID symbolId) {
        symbolService.removeById(symbolId);
        return ResponseEntity.ok().build();
    }
}
