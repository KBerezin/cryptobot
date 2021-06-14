package com.bootybanger.cryptobot.integration.core.service.asset;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.mapper.AssetDTOMapper;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.GateAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.asset.client.GateAssetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GateAssetIntegrationServiceImpl implements GateAssetIntegrationService {

    private final AssetDTOMapper mapper;
    private final GateAssetClient client;

    @Override
    public Mono<List<AssetDTO>> getAllAssets() {
        return client.getGateAssets().map(mapper::toAssetDTO);
    }
}
