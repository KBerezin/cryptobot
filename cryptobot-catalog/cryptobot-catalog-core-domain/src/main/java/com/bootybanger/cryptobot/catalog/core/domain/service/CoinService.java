package com.bootybanger.cryptobot.catalog.core.domain.service;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;

import java.util.List;

public interface CoinService {
    void addAll(List<CoinDTO> coinDTOList);
    List<CoinDTO> getAll();
}
