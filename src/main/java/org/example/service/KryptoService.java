package org.example.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.bitstamp.BitstampExchange;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KryptoService {
    private final MarketDataService data;
    private final DbService dbService;

    // połączenie z giełdą Bitstamp, pobieranie kursów.
    public KryptoService() {
        Exchange bitstamp = new BitstampExchange();
        bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class);
        this.data = bitstamp.getMarketDataService();
        this.dbService = new DbService();
    }

    // pobranie kursów dla pary walutowej i zwrócenie Tickera
    public Ticker getData(String baseCurrency, String counterCurrency) throws Exception {
        return data.getTicker(new CurrencyPair(baseCurrency, counterCurrency));
    }

    // zapisywanie kursów pięciu kryptowalut do tabel xxx_usd_historyczne
    public void saveCryptoRatesToUSD() {
        String[] cryptocurrencies = {"BTC", "ETH", "LTC", "XRP", "BCH"};
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (String crypto : cryptocurrencies) {
            try {
                Ticker ticker = getData(crypto, "USD");
                dbService.insertCryptoRateToUSD(crypto.toLowerCase() + "_usd_historyczne", now.format(dateFormatter), now.format(timeFormatter), crypto, ticker.getLast().doubleValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
