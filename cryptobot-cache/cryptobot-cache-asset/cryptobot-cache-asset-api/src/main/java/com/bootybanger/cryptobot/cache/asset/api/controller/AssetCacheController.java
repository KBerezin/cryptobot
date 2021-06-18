package com.bootybanger.cryptobot.cache.asset.api.controller;

import com.bootybanger.cryptobot.cache.asset.core.domain.service.AssetCacheService;
import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/cache/asset")
public class AssetCacheController {
    private final AssetCacheService assetCacheService;

    @GetMapping("/get/all")
    public Mono<Map<SymbolDTO, Set<AssetDTO>>> getAllAssets() {
        return assetCacheService.getAssetMap();
    }

    @PostMapping("/add/list")
    public Mono<Void> putAssetList(@RequestBody List<AssetDTO> assetDTOList) {
        return assetCacheService.put(assetDTOList);
    }
}