package org.example.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.bitstamp.BitstampExchange;

public class KryptoService {
    private final MarketDataService data;

    //połączenie z giełdą Bitstamp, pobieranie kursów.
    public KryptoService() {
        Exchange bitstamp = new BitstampExchange();
        bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class);
        this.data = bitstamp.getMarketDataService();
    }
    //pobranie kursów dla pary walutowej i zwrócenie Tickera
    public Ticker getData(String baseCurrency, String counterCurrency) throws Exception {
        return data.getTicker(new CurrencyPair(baseCurrency, counterCurrency));
    }
}
