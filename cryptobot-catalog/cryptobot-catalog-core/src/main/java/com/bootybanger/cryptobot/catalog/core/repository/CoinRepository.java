package com.bootybanger.cryptobot.catalog.core.repository;

import com.bootybanger.cryptobot.catalog.core.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoinRepository extends JpaRepository<Coin, UUID> {
}
