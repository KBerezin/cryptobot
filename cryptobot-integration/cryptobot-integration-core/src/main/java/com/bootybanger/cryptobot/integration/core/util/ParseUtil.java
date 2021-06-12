package com.bootybanger.cryptobot.integration.core.util;

import com.bootybanger.cryptobot.common.constant.dto.CoinDTO;
import com.bootybanger.cryptobot.common.constant.dto.ExchangeSymbolDTO;
import com.bootybanger.cryptobot.common.constant.enumeration.CryptoExchange;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Getter
public class ParseUtil {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<ExchangeSymbolDTO> parseListExchangeSymbols(String json, Map<String, String> nodeNameMap) {
        List<ExchangeSymbolDTO> exchangeSymbolDTOList = new ArrayList<>();
        try {
            JsonNode tree = objectMapper.readTree(json);
            JsonNode data = tree.findValue(nodeNameMap.get("listNode"));
            data.forEach(jsonNode -> {
                ExchangeSymbolDTO exchangeSymbolDTO = getExchangeSymbolFromJsonNode(jsonNode, nodeNameMap);
                exchangeSymbolDTOList.add(exchangeSymbolDTO);
            });
        } catch (JsonProcessingException e) {
            //TODO логгер
            e.printStackTrace();
        }
        return exchangeSymbolDTOList;
    }

    private ExchangeSymbolDTO getExchangeSymbolFromJsonNode(JsonNode jsonNode, Map<String, String> nodeNameMap) {
        String symbolName = jsonNode.get(nodeNameMap.get("symbolNode")).asText("");
        String symbol = handleSymbolName(symbolName, nodeNameMap.get("exchangeName"));
        return ExchangeSymbolDTO.builder().symbol(symbol).build();
    }

    private String handleSymbolName(String symbolName, String exchangeName) {
        CryptoExchange cryptoExchange = CryptoExchange.valueOf(exchangeName);
        switch (cryptoExchange) {
            case KUCOIN:
                return symbolName.replace("-", "");
            default:
                return symbolName;
        }
    }

    public List<CoinDTO> parseListCoins(String json, Map<String, String> nodeNameMap) {
        List<CoinDTO> coinDTOList = new ArrayList<>();
        try {
            JsonNode tree = objectMapper.readTree(json);
            JsonNode data = tree.findValue(nodeNameMap.get("listNode"));
            data.forEach(jsonNode -> {
                CoinDTO coinDTO = getCoinFromJsonNode(jsonNode, nodeNameMap);
                coinDTOList.add(coinDTO);
            });
        } catch (JsonProcessingException e) {
            //TODO логгер
            e.printStackTrace();
        }
        return coinDTOList.stream().filter(coin -> !handleDuplicates(coin)).collect(Collectors.toList());
    }

    public CoinDTO getCoinFromJsonNode(JsonNode coinNode, Map<String, String> nodeNameMap) {
        String name = coinNode.get(nodeNameMap.get("nameNode")).asText("");
        String symbol = coinNode.get(nodeNameMap.get("symbolNode")).asText("");
        Integer rank = coinNode.get(nodeNameMap.get("rankNode")).asInt(-1);
        String platform = getNodeFieldAsString(coinNode.get(nodeNameMap.get("platformNode")), "name");
        return CoinDTO.builder().name(name).symbol(symbol).rank(rank).platform(platform)
                .build();
    }

    public String getNodeFieldAsString(JsonNode jsonNode, String fieldName) {
        if (jsonNode == null || jsonNode.get(fieldName) == null) {
            return "";
        }
        return jsonNode.get(fieldName).asText();
    }

    private boolean handleDuplicates(CoinDTO coinDTO) {
        Map<String, String> duplicateNameMap = new HashMap<>();
        duplicateNameMap.put("BLOCKIDCOIN", "BID1");
        duplicateNameMap.put("DeFi Bids", "BID2");
        duplicateNameMap.put("Bidao", "BID3");
        duplicateNameMap.put("Elevate", "ELE1");
        duplicateNameMap.put("Smart Trade Coin", "TRADE1");
        duplicateNameMap.put("IFToken", "IFT1");
        duplicateNameMap.put("Bitstar", "BITS1");
        duplicateNameMap.put("Bitcoinus", "BITS2");
        duplicateNameMap.put("Bit", "BITS3");
        duplicateNameMap.put("Bela Aqua", "AQUA2");
        duplicateNameMap.put("Hydro Protocol", "HOT2");
        duplicateNameMap.put("SSS Finance", "SSS1");
        duplicateNameMap.put("CateCoin", "CATE1");
        duplicateNameMap.put("Blue Baikal", "BBC1");
        duplicateNameMap.put("BigBang Core", "BBC2");
        duplicateNameMap.put("Game.com", "GTC1");
        duplicateNameMap.put("Bitether", "BTR1");
        duplicateNameMap.put("Jetfuel Finance", "FUEL1");
        duplicateNameMap.put("Bitcoin Asset", "BTA1");
        duplicateNameMap.put("sETH", "SETH1");
        duplicateNameMap.put("PocMon", "PMON1");
        duplicateNameMap.put("Auroracoin", "AUR1");
        duplicateNameMap.put("FlourMix", "FLO1");
        duplicateNameMap.put("SherLOCK Security", "LOCK1");
        duplicateNameMap.put("Newsolution", "NST1");
        duplicateNameMap.put("Shadetech", "SHD1");
        duplicateNameMap.put("Mithril Share", "MIS1");
        duplicateNameMap.put("BioNTech tokenized stock FTX", "BNTX1");
        duplicateNameMap.put("BioNTech tokenized stock Bittrex", "BNTX2");
        duplicateNameMap.put("Knekted", "KNT1");
        duplicateNameMap.put("ApeSwap Finance", "BANANA1");
        duplicateNameMap.put("Babytoken", "BABY1");
        duplicateNameMap.put("Anti-Lockdown", "FREE1");
        duplicateNameMap.put("Opus", "OPT1");
        duplicateNameMap.put("MicroTuber", "MCT1");
        duplicateNameMap.put("MCOBIT", "MCT2");
        duplicateNameMap.put("Consentium", "CSM1");
        duplicateNameMap.put("Fire Token", "FIRE1");
        duplicateNameMap.put("Fire Protocol", "FIRE2");
        duplicateNameMap.put("Truebit", "TRU1");
        duplicateNameMap.put("Usechain Token", "USE1");
        duplicateNameMap.put("BitcoinV", "BTCV1");
        duplicateNameMap.put("Name Change Token", "NCT1");
        duplicateNameMap.put("Eternal Cash", "EC1");




        return null;
    }

    private boolean isExcludedCoinName() {
        Arrays.asList("KUN");
    }
}
