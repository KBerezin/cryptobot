package com.bootybanger.cryptobot.common.constant.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BinanceSymbolDTOResponseContainer {
    private List<BinanceSymbolDTOResponse> symbols;
}
