package com.bootybanger.cryptobot.integration.core.service.asset;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.mapper.AssetDTOMapper;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.HuobiIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.asset.client.HuobiAssetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HuobiAssetIntegrationServiceImpl implements HuobiIntegrationService {

    private final AssetDTOMapper mapper;
    private final HuobiAssetClient client;

    @Override
    public Mono<List<AssetDTO>> getAllAssets() {
        return client.getHuobiAssets().map(mapper::toAssetDTO);
    }
}
