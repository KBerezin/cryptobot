package com.bootybanger.cryptobot.catalog.api.controller;

import com.bootybanger.cryptobot.catalog.core.domain.service.CoinService;
import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/catalog/coin")
public class CoinController {
    private final CoinService coinService;

    @GetMapping("/get/list/all")
    public ResponseEntity<List<CoinDTO>> getAllCoins() {
        return ResponseEntity.ok(coinService.getAll());
    }

    @PostMapping("/add/list")
    public ResponseEntity<Void> addCoins(@RequestBody List<CoinDTO> coins) {
        if (!coins.isEmpty()) {
            coinService.addAll(coins);
        }
        return ResponseEntity.ok().build();
    }
}
