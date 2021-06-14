package com.bootybanger.cryptobot.common.constant.mapper;

import com.bootybanger.cryptobot.common.constant.dto.AssetDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeAssetDTO;

import java.util.List;

public interface AssetDTOMapper {
    AssetDTO toAssetDTO(ExchangeAssetDTO exchangeAssetDTO);
    List<AssetDTO> toAssetDTO(List<ExchangeAssetDTO> exchangeAssetDTOList);
}
