package com.bootybanger.cryptobot.catalog.core.repository;

import com.bootybanger.cryptobot.catalog.core.entity.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface SymbolRepository extends JpaRepository<Symbol, UUID> {
    Optional<Symbol> findByName(String name);
    Boolean existsByName(String name);
}
