package com.bootybanger.cryptobot.integration.asset.core.domain.util;

import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface AssetParseUtil {
    List<ExchangeAssetDTO> parseAssetListJson(String json);

    List<ExchangeAssetDTO> getExchangeAssetDTOListFromNode(JsonNode assetListNode);

    ExchangeAssetDTO getExchangeAssetDTOFromNode(JsonNode assetNode);
}
