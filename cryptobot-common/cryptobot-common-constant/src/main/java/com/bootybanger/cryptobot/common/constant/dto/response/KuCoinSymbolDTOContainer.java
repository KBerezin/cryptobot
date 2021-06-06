package com.bootybanger.cryptobot.common.constant.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class KuCoinSymbolDTOContainer {
    private final List<KuCoinSymbolDtoResponse> ticker;
}
