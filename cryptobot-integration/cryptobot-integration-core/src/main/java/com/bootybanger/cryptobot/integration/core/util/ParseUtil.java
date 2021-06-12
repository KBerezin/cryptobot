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
        duplicateNameMap.put("GHOSTPRISM", "GHOST1");
        duplicateNameMap.put("ODE", "ODE1");
        duplicateNameMap.put("Crowd Machine", "CMCT1");
        duplicateNameMap.put("SatisFinance Token", "SAT1");
        duplicateNameMap.put("Bat True Share", "BTS1");
        duplicateNameMap.put("YouSwap", "YOU1");
        duplicateNameMap.put("Etheroll", "DICE1");
        duplicateNameMap.put("TRONbetDice", "DICE2");
        duplicateNameMap.put("Bitalgo", "ALG1");
        duplicateNameMap.put("Crudeoil Finance", "OIL1");
        duplicateNameMap.put("Karma DAO", "KARMA1");
        duplicateNameMap.put("ZUM TOKEN", "ZUM1");
        duplicateNameMap.put("EarnBet", "BET1");
        duplicateNameMap.put("Yield Farming Token", "YFT1");
        duplicateNameMap.put("THORChain (ERC20)", "RUNE1");
        duplicateNameMap.put("Rune", "RUNE2");
        duplicateNameMap.put("Blocklancer", "LNC1");
        duplicateNameMap.put("snglsDAO", "SGT1");
        duplicateNameMap.put("Parallel", "PAR1");
        duplicateNameMap.put("GuccioneCoin", "GCC1");
        duplicateNameMap.put("Hybrid Bank Cash", "HBC1");
        duplicateNameMap.put("NFTX Hashmasks Index", "MASK1");
        duplicateNameMap.put("PocketNode", "NODE1");
        duplicateNameMap.put("Profile Utility Token", "PUT1");
        duplicateNameMap.put("FunKeyPay", "FNK1");
        duplicateNameMap.put("Octree", "OCT1");
        duplicateNameMap.put("Global X Change Token", "GXT1");
        duplicateNameMap.put("OWL", "OWL1");
        duplicateNameMap.put("FEX Token", "FEX1");
        duplicateNameMap.put("Rai Reflex Index", "RAI1");
        duplicateNameMap.put("Acuity Token", "ACU1");
        duplicateNameMap.put("BOX Token", "BOX1");
        duplicateNameMap.put("DefiBox", "BOX2");
        duplicateNameMap.put("Marblecoin", "MBC1");
        duplicateNameMap.put("Snowball", "SBT1");
        duplicateNameMap.put("Depth Token", "DEP1");
        duplicateNameMap.put("Billibilli Inc tokenized stock FTX", "BILI1");
        duplicateNameMap.put("Cube", "AUTO1");
        duplicateNameMap.put("ORS Group", "ORS1");
        duplicateNameMap.put("MoonJuice", "MOJO1");
        duplicateNameMap.put("Kindcow Finance", "KIND1");
        duplicateNameMap.put("Cross Finance", "CRP1");
        duplicateNameMap.put("Rich Maker", "RICH1");
        duplicateNameMap.put("DoDreamChain", "DRM1");
        duplicateNameMap.put("ZOM", "ZOM1");
        duplicateNameMap.put("Comet", "CMT1");
        duplicateNameMap.put("PIXL", "PXL1");
        duplicateNameMap.put("Universe", "UNI1");
        duplicateNameMap.put("UNICORN Token", "UNI2");
        duplicateNameMap.put("BiFi", "BIFI1");
        duplicateNameMap.put("Bitcoin File", "BIFI2");
        duplicateNameMap.put("LinkToken", "LTK1");
        duplicateNameMap.put("BLOCKCLOUT", "CLOUT1");
        duplicateNameMap.put("Wisdom Chain", "WDC");
        duplicateNameMap.put("StakeCubeCoin", "SCC1");
        duplicateNameMap.put("SiaCashCoin", "SCC2");
        duplicateNameMap.put("StockChain", "SCC3");
        duplicateNameMap.put("SCC DIGforIT", "SCC4");
        duplicateNameMap.put("Caramel Swap", "MEL1");
        duplicateNameMap.put("Social Rocket", "ROCKS1");
        duplicateNameMap.put("SuperCoin", "SUPER1");
        duplicateNameMap.put("Resfinex Token", "RES1");
        duplicateNameMap.put("Open Governance Token", "OPEN1");
        duplicateNameMap.put("Xstable.Protocol", "XST1");
        duplicateNameMap.put("BITTUP", "BTU1");
        duplicateNameMap.put("Infinity Economics", "XIN1");
        duplicateNameMap.put("Stox", "STX1");
        duplicateNameMap.put("Dfinance", "XFI1");
        duplicateNameMap.put("STK Coin", "STK1");
        duplicateNameMap.put("Buxcoin", "BUX1");
        duplicateNameMap.put("LEXIT", "LXT1");
        duplicateNameMap.put("Gravity Finance", "GFI1");
        duplicateNameMap.put("Sora Validator Token", "VAL1");
        duplicateNameMap.put("Valkyrie Network", "VAL2");
        duplicateNameMap.put("Mercurial Finance", "MER1");
        duplicateNameMap.put("GMB", "GMB1");
        duplicateNameMap.put("Triforce Protocol", "TFC1");
        duplicateNameMap.put("Web Coin Pay", "WEC1");
        duplicateNameMap.put("Polywolf", "MOON1");
        duplicateNameMap.put("Don-key", "DON1");
        duplicateNameMap.put("DEONEX COIN", "DON2");
        duplicateNameMap.put("MIR COIN", "MIR1");
        duplicateNameMap.put("LaikaCoin", "LAIKA1");
        duplicateNameMap.put("SONO", "SONO1");
        duplicateNameMap.put("LNAsolution Coin", "LAS1");
        duplicateNameMap.put("iCherry Finance", "ICH1");
        duplicateNameMap.put("Helpico", "HELP1");
        duplicateNameMap.put("Actinium", "ACM1");
        duplicateNameMap.put("Cashbery Coin", "CBC1");
        duplicateNameMap.put("CryptoBharatCoin", "CBC2");
        duplicateNameMap.put("CryptoBossCoin", "CBC3");
        duplicateNameMap.put("Galactrum", "ORE1");
        duplicateNameMap.put("OnX Finance", "ONX1");
        duplicateNameMap.put("Exchange Payment Coin", "EXP1");
        duplicateNameMap.put("Oracolxor", "XOR1");
        duplicateNameMap.put("AI Network", "AIN1");
        duplicateNameMap.put("TENA [new]", "TENA1");
        duplicateNameMap.put("Doraemoon", "DORA1");
        duplicateNameMap.put("Capital.Finance", "CAP1");
        duplicateNameMap.put("VAIOT", "VAI1");
        duplicateNameMap.put("Dark Energy Crystals", "DEC1");
        duplicateNameMap.put("Distributed Energy Coin", "DEC2");
        duplicateNameMap.put("BigONE Token", "ONE1");
        duplicateNameMap.put("DeFi Nation Signals DAO", "DSD1");
        duplicateNameMap.put("MintCoin", "MINT1");
        duplicateNameMap.put("Try.Finance", "TRY1");
        duplicateNameMap.put("Blockcloud", "BLOC1");
        duplicateNameMap.put("Tacos", "TACO1");
        duplicateNameMap.put("Taco Finance", "TACO2");
        duplicateNameMap.put("PLANET", "PLA1");
        duplicateNameMap.put("PlayDapp", "PLA2");
        duplicateNameMap.put("PlayChip", "PLA3");
        duplicateNameMap.put("BidiPass", "BDP1");
        duplicateNameMap.put("CryptoSoul", "SOUL1");
        duplicateNameMap.put("ChainZ Arena", "SOUL2");
        duplicateNameMap.put("APOyield", "SOUL3");
        duplicateNameMap.put("Becaz", "BCZ1");
        duplicateNameMap.put("Maxcoin", "MAX1");
        duplicateNameMap.put("RushMoon", "RUSH1");
        duplicateNameMap.put("DeGate", "DG1");
        duplicateNameMap.put("Chi Gastoken", "CHI1");
        duplicateNameMap.put("Echelon DAO", "ECHO1");
        duplicateNameMap.put("FINANCIAL INVESTMENT TOKEN", "FIT1");
        duplicateNameMap.put("FIRST INTERCHANGEABLE TOKEN", "FIT2");
        duplicateNameMap.put("Memetic / PepeCoin", "MEME1");
        duplicateNameMap.put("Metaverse NFT Index", "PLAY1");
        duplicateNameMap.put("Play Royal", "PLAY2");
        duplicateNameMap.put("Goatcoin", "GOAT1");
        duplicateNameMap.put("Google tokenized stock Bittrex", "GOOGL1");
        duplicateNameMap.put("Centaure", "CEN1");
        duplicateNameMap.put("BondAppétit Governance Token", "BAG1");
        duplicateNameMap.put("CryptoTycoon", "CTT1");
        duplicateNameMap.put("CITEX Token", "CTT2");
        duplicateNameMap.put("Culture Ticket Chain", "CTC1");
        duplicateNameMap.put("iBTC (Synthetix)", "IBTC1");
        duplicateNameMap.put("Lady Luck", "LUCK1");
        duplicateNameMap.put("NFTTONE", "TONE1");
        duplicateNameMap.put("Decurian", "ECU1");
        duplicateNameMap.put("Stone DeFi", "STN1");
        duplicateNameMap.put("Marscoin", "MARS1");
        duplicateNameMap.put("VELOREX", "VEX1");
        duplicateNameMap.put("NFT", "NFT1");
        duplicateNameMap.put("Stater", "STR1");
        duplicateNameMap.put("Jigstack", "STAK1");
        duplicateNameMap.put("Evolution", "GEN1");
        duplicateNameMap.put("STVKE", "STV1");
        duplicateNameMap.put("LegalBlock", "LBK1");
        duplicateNameMap.put("CPUcoin", "CPU1");
        duplicateNameMap.put("XPToken.io", "XPT1");
        duplicateNameMap.put("NoCapCoin", "NCC1");
        duplicateNameMap.put("Center Prime", "CPX1");
        duplicateNameMap.put("Pfizer tokenized stock Bittrex", "PFE1");
        duplicateNameMap.put("Voucher", "DVS1");
        duplicateNameMap.put("SafeBlast", "BLAST1");
        duplicateNameMap.put("Pegazus finance", "PEG1");
        duplicateNameMap.put("Bitsonic", "BSC1");
        duplicateNameMap.put("BSC FARM", "BSC2");
        duplicateNameMap.put("Chess Coin", "CHESS1");
        duplicateNameMap.put("YFIII", "YFIII1");
        duplicateNameMap.put("Fastcoin", "FST1");
        duplicateNameMap.put("Futureswap", "FST2");
        duplicateNameMap.put("Game Of DeFi", "GOD1");
        duplicateNameMap.put("EncrypGen", "DNA1");
        duplicateNameMap.put("Thore Cash", "TCH1");
        duplicateNameMap.put("Tchain", "TCH2");
        duplicateNameMap.put("THECASH", "TCH3");
        duplicateNameMap.put("City Coin", "CITY1");
        duplicateNameMap.put("Soda Coin", "SOC1");
        duplicateNameMap.put("PiSwap Token", "PIS1");
        duplicateNameMap.put("Bitcoin Classic", "BXC1");
        duplicateNameMap.put("Rise", "RISE1");
        duplicateNameMap.put("PayUSD", "PUSD1");
        duplicateNameMap.put("PegsUSD", "PUSD2");
        duplicateNameMap.put("TokenSwap", "TOP1");
        duplicateNameMap.put("Fantasy Sports", "DFS1");
        duplicateNameMap.put("CoFiX", "COFI1");
        duplicateNameMap.put("Uniswap Finance", "UNFI1");
        duplicateNameMap.put("SoMee.Social", "ONG1");
        duplicateNameMap.put("Moon YFI", "MYFI1");
        duplicateNameMap.put("IOV BlockChain", "IOV1");
        duplicateNameMap.put("UniFi Protocol", "UP1");
        duplicateNameMap.put("Refract", "RFR1");
        duplicateNameMap.put("DapperCoin", "DAPP1");
        duplicateNameMap.put("FairCoin", "FAIR1");
        duplicateNameMap.put("Fairmoon", "FAIR2");
        duplicateNameMap.put("Coinwaycoin", "CAN1");
        duplicateNameMap.put("CanYaCoin", "CAN2");
        duplicateNameMap.put("Netkoin", "NTK1");
        duplicateNameMap.put("WOWswap", "WOW1");
        duplicateNameMap.put("World of Waves", "WOW2");
        duplicateNameMap.put("KEY", "KEY1");
        duplicateNameMap.put("MoMo KEY", "KEY2");
        duplicateNameMap.put("Super Bitcoin", "SBTC1");
        duplicateNameMap.put("Shield protocol", "SLD1");
        duplicateNameMap.put("Hubii Network", "HBT1");
        duplicateNameMap.put("ANTcoin", "ANT1");
        duplicateNameMap.put("Dracula Token", "DRC1");
        duplicateNameMap.put("DRC mobility", "DRC2");
        duplicateNameMap.put("Dragon Token", "DT1");
        duplicateNameMap.put("Vision", "VSN1");
        duplicateNameMap.put("Blockmason Link", "BLINK1");
        duplicateNameMap.put("HyperGraph", "HGT1");
        duplicateNameMap.put("Chainpay", "CPAY1");
        duplicateNameMap.put("Bankroll Vault", "VLT1");
        duplicateNameMap.put("ProSwap", "PROS1");
        duplicateNameMap.put("CORN", "CORN1");
        duplicateNameMap.put("Popcorn", "CORN2");
        duplicateNameMap.put("The Famous Token", "TFT1");
        duplicateNameMap.put("Oracle System", "ORC1");
        duplicateNameMap.put("Originate Coin", "ORC2");
        duplicateNameMap.put("Datamine FLUX", "FLUX1");
        duplicateNameMap.put("Flux Protocol", "FLUX2");
        duplicateNameMap.put("MixTrust", "MXT1");
        duplicateNameMap.put("LINA", "LINA1");
        duplicateNameMap.put("Dipper Network", "DIP1");
        duplicateNameMap.put("Deipool", "DIP2");
        duplicateNameMap.put("Moms I'd Like to Fund", "MILF1");
        duplicateNameMap.put("Digital Gold", "GOLD1");
        duplicateNameMap.put("Golden Token", "GOLD2");
        duplicateNameMap.put("Fitmin Finance", "FTM1");
        duplicateNameMap.put("Metacoin", "MTC1");
        duplicateNameMap.put("Epanus", "EPS1");
        duplicateNameMap.put("MM Token", "MM1");
        duplicateNameMap.put("The Gemstone", "GST1");
        duplicateNameMap.put("Amazon tokenized stock FTX", "AMZN1");
        duplicateNameMap.put("Reflexer Ungovernance Token", "FLX1");
        duplicateNameMap.put("CheeseSwap", "CHS1");
        duplicateNameMap.put("Maverick Chain", "MVC1");
        duplicateNameMap.put("Alibaba tokenized stock FTX", "BABA1");
        duplicateNameMap.put("MeetPle", "MPT1");
        duplicateNameMap.put("DeFireX", "DFX1");
        duplicateNameMap.put("SafeCoin", "SAFE1");
        duplicateNameMap.put("Terra SDT", "SDT1");
        duplicateNameMap.put("Facebook tokenized stock FTX", "FB1");
        duplicateNameMap.put("Luna Coin", "LUNA1");
        duplicateNameMap.put("Midas Dollar Share", "MDS1");
        duplicateNameMap.put("Mechashiba", "MEC1");
        duplicateNameMap.put("STABLE ASSET", "STA1");
        duplicateNameMap.put("COVIR.IO", "CVR1");
        duplicateNameMap.put("Tenet", "TEN1");
        duplicateNameMap.put("Ethereum Vault", "ETHV1");
        duplicateNameMap.put("Rimbit", "RBT1");
        duplicateNameMap.put("HecoFi", "HFI1");
        duplicateNameMap.put("Stacker Ventures", "STACK1");
        duplicateNameMap.put("Alchemist", "MIST1");
        duplicateNameMap.put("Alchemist DeFi Mist", "MIST2");
        duplicateNameMap.put("PizzaSwap", "PIZZA1");
        duplicateNameMap.put("SafePizza", "PIZZA2");
        duplicateNameMap.put("eosBLACK", "BLACK1");
        duplicateNameMap.put("FXT Token", "FXT1");
        duplicateNameMap.put("GeoCoin", "GEO1");
        duplicateNameMap.put("Apple tokenized stock FTX", "AAPL1");
        duplicateNameMap.put("Fanaticos Cash", "FCH1");
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
