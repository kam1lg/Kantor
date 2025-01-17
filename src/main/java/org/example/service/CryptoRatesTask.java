package org.example.service;

import org.example.models.Rates;
import org.knowm.xchange.dto.marketdata.Ticker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class CryptoRatesTask extends TimerTask {
    @Override
    public void run() {
        // Zapisanie kursów kryptowalut do tabeli kursy_krypto
        saveCryptoRates();

        // Zapisanie kursów kryptowalut do tabel xxx_usd_historyczne
        saveCryptoRatesToUSD();
    }

    // Funkcja zapisująca kursy kryptowalut do tabeli kursy_krypto
    public static void saveCryptoRates() {
        DbService dbService = new DbService();
        KryptoService serwisKrypto = new KryptoService();
        List<String> cryptoCurrencies = Arrays.asList("BTC", "ETH", "LTC", "XRP", "BCH");
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        String date = sdfDate.format(new Date());
        String time = sdfTime.format(new Date());

        for (String crypto : cryptoCurrencies) {
            try {
                Ticker ticker = serwisKrypto.getData(crypto, "USD");
                Double rate = ticker.getLast().doubleValue();
                dbService.insertCryptoRate(date, time, crypto, rate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Funkcja zapisująca kursy kryptowalut do tabel xxx_usd_historyczne
    public static void saveCryptoRatesToUSD() {
        try {
            KryptoService serwisKrypto = new KryptoService();
            serwisKrypto.saveCryptoRatesToUSD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
