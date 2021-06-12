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
        duplicateNameMap.put("ROAD", "ROAD1");
        duplicateNameMap.put("FinexboxToken", "FNB1");
        duplicateNameMap.put("Bitcoin and Ethereum Standard Token", "BEST1");
        duplicateNameMap.put("hodlearn", "HODL1");
        duplicateNameMap.put("BuySell", "BULL1");
        duplicateNameMap.put("Gric Coin", "GC1");
        duplicateNameMap.put("Golden Ratio Token", "GRT1");
        duplicateNameMap.put("TheToken.Network", "TTN1");
        duplicateNameMap.put("Beyond Meat Inc tokenized stock Bittrex", "BYND1");
        duplicateNameMap.put("Ubique Chain Of Things", "UCT1");
        duplicateNameMap.put("Lition", "LIT1");
        duplicateNameMap.put("Trustworks", "TRUST1");
        duplicateNameMap.put("KIWI TOKEN", "KIWI1");
        duplicateNameMap.put("Blue Swap", "BLUE1");
        duplicateNameMap.put("Corgi inu", "CORGI1");
        duplicateNameMap.put("BFis.Finance", "BFI1");
        duplicateNameMap.put("Bearn", "BFI2");
        duplicateNameMap.put("Edgeless", "EDG1");
        duplicateNameMap.put("Rocket Bunny", "BUNNY1");
        duplicateNameMap.put("POP Network Token", "POP1");
        duplicateNameMap.put("Bankless DAO", "BANK1");
        duplicateNameMap.put("Bankcoin", "BANK2");
        duplicateNameMap.put("Exohood", "EXO1");
        duplicateNameMap.put("Eco Value Coin", "EVC1");
        duplicateNameMap.put("Evrice", "EVC2");
        duplicateNameMap.put("protocol finance", "PFI1");
        duplicateNameMap.put("Compound Coin", "COMP1");
        duplicateNameMap.put("CCSwap", "CC1");
        duplicateNameMap.put("Themis", "GET1");
        duplicateNameMap.put("Gire Token", "GET2");
        duplicateNameMap.put("Sheesha Finance [ERC20]", "SHEESHA1");
        duplicateNameMap.put("ExtStock Token", "XT1");
        duplicateNameMap.put("Basis Dollar", "BSD1");
        duplicateNameMap.put("Mirror Farm", "MOR1");
        duplicateNameMap.put("Bitcoin True", "BTCT1");
        duplicateNameMap.put("Connectico", "CON1");
        duplicateNameMap.put("Converter.Finance", "CON2");
        duplicateNameMap.put("BOSCore", "BOS1");
        duplicateNameMap.put("Bitpayer Token", "BPT1");
        duplicateNameMap.put("Bitpumps Token", "BPT2");
        duplicateNameMap.put("Bitbot Protocol", "BBP1");
        duplicateNameMap.put("BitMoney", "BIT1");
        duplicateNameMap.put("SPINDLE", "SPD1");
        duplicateNameMap.put("Bitcoin Free Cash", "BFC1");
        duplicateNameMap.put("Bit Financial", "BFC2");
        duplicateNameMap.put("SappChain", "SAPP1");
        duplicateNameMap.put("mStable BTC", "MBTC1");
        duplicateNameMap.put("Lightstreams", "PHT1");
        duplicateNameMap.put("Unknown Fair Object", "UFO1");
        duplicateNameMap.put("Relevant", "REL1");
        duplicateNameMap.put("Unobtanium", "UNO1");
        duplicateNameMap.put("CURE Farm", "CURE1");
        duplicateNameMap.put("UCROWDME", "UCM1");
        duplicateNameMap.put("TON Token", "TON1");
        duplicateNameMap.put("TON Crystal", "TON2");
        duplicateNameMap.put("AT Finance", "AT1");
        duplicateNameMap.put("ABCC Token", "AT2");
        duplicateNameMap.put("Phoswap", "PHO1");
        duplicateNameMap.put("DUO Network Token", "DUO1");
        duplicateNameMap.put("Gold Coin Reserve", "GCR1");
        duplicateNameMap.put("WingShop", "WING1");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");
        duplicateNameMap.put("", "");




        return excludedNames.contains(coinDTO.getName());
    }

    private boolean isExcludedCoinName() {
        Arrays.asList("KUN");
    }
}
