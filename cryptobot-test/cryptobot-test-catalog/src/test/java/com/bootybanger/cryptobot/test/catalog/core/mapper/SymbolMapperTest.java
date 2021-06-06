package com.bootybanger.cryptobot.test.catalog.core.mapper;

import com.bootybanger.cryptobot.catalog.core.entity.Symbol;
import com.bootybanger.cryptobot.catalog.core.mapper.SymbolMapper;
import com.bootybanger.cryptobot.common.constant.dto.SymbolDTO;
import com.bootybanger.cryptobot.test.config.MapperTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ContextConfiguration(classes = MapperTestConfig.class)
public class SymbolMapperTest {

    @Autowired
    private SymbolMapper symbolMapper;

    @Test
    public void shouldWork() {
        Symbol symbol = new Symbol();
        symbol.setName("BTC_ETH");
        SymbolDTO symbolDTO = symbolMapper.toDto(symbol);
        Assertions.assertEquals(symbol.getId(), symbolDTO.getId());
        Assertions.assertEquals(symbol.getName(), symbolDTO.getName());
    }
}
