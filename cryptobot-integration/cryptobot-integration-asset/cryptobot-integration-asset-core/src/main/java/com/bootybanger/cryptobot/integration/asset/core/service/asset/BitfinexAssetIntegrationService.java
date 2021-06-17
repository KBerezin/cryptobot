package com.bootybanger.cryptobot.integration.asset.core.service.asset;

import com.bootybanger.cryptobot.common.constant.mapper.AssetDTOMapper;
import com.bootybanger.cryptobot.integration.asset.core.domain.service.AbstractAssetIntegrationService;
import com.bootybanger.cryptobot.integration.asset.core.domain.service.ExchangeAssetClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BitfinexAssetIntegrationService extends AbstractAssetIntegrationService {
    public BitfinexAssetIntegrationService(AssetDTOMapper mapper,
                                           @Qualifier("bitfinexAssetClient") ExchangeAssetClient client) {
        super(mapper, client);
    }
}
