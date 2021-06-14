package com.bootybanger.cryptobot.integration.core.service.asset;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.mapper.AssetDTOMapper;
import com.bootybanger.cryptobot.integration.core.domain.service.asset.KuCoinAssetIntegrationService;
import com.bootybanger.cryptobot.integration.core.service.asset.client.KuCoinAssetClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KuCoinAssetIntegrationServiceImpl implements KuCoinAssetIntegrationService {

    private final AssetDTOMapper mapper;
    private final KuCoinAssetClient client;

    @Override
    public Mono<List<AssetDTO>> getAllAssets() {
        return client.getKuCoinAssets().map(mapper::toAssetDTO);
    }
}
