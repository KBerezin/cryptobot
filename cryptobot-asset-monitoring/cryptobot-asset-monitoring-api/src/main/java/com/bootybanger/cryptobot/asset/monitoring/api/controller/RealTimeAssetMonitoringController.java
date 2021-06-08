package com.bootybanger.cryptobot.asset.monitoring.api.controller;
/*
import com.bootybanger.cryptobot.asset.monitoring.core.domain.service.RealTimeAssetMonitoringService;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/monitoring/asset")
public class RealTimeAssetMonitoringController {
    private final RealTimeAssetMonitoringService assetMonitoringService;

    @GetMapping("/get/all")
    public Mono<Map<SymbolDTO, List<AssetDTO>>> getAllAssets() {
        return assetMonitoringService.getAssetMap();
    }

    @PostMapping("/add/list")
    public Mono<Void> putAssetList(@RequestBody List<AssetDTO> assetDTOList) {
        return assetMonitoringService.put(assetDTOList);
    }
}
*/