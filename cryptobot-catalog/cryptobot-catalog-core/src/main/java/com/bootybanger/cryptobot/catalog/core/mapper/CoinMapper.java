package com.bootybanger.cryptobot.catalog.core.mapper;

import com.bootybanger.cryptobot.catalog.core.entity.Coin;
import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoinMapper extends CommonEntityDtoMapper<CoinDTO, Coin> {
}
