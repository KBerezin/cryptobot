package com.bootybanger.cryptobot.catalog.core.service;

import com.bootybanger.cryptobot.catalog.core.domain.service.CoinService;
import com.bootybanger.cryptobot.catalog.core.entity.Coin;
import com.bootybanger.cryptobot.catalog.core.mapper.CoinMapper;
import com.bootybanger.cryptobot.catalog.core.repository.CoinRepository;
import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CoinServiceImpl implements CoinService {

    private final CoinMapper coinMapper;
    private final CoinRepository coinRepository;

    @Override
    public void addAll(List<CoinDTO> coinDTOList) {
        List<Coin> coins = coinMapper.toEntity(coinDTOList);
        coinRepository.saveAll(coins);
    }

    @Override
    public List<CoinDTO> getAll() {
        List<Coin> allCoins = coinRepository.findAll();
        return coinMapper.toDto(allCoins);
    }
}
