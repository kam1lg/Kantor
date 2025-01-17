package org.example.service;

import org.example.models.Rates;
import org.knowm.xchange.dto.marketdata.Ticker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class Task extends TimerTask {
    @Override
    public void run() {
        try {
            // Pobranie wszystkich walut z NBP
            List<Rates> ratesList = getAllCurrenciesFromNBP();

            // Wyświetlenie wszystkich walut i ich kursów
                /*for (Rates rate : ratesList) {
                    System.out.println(rate.code() + ": " + rate.mid());
                }*/

            // Zapisanie kursów walut do tabeli kursy_fiat
            saveFiatRates(ratesList);

            // Pobranie kursów kryptowalut i zapisanie do tabeli kursy_krypto
            saveCryptoRates();

            // Pobranie kursów kryptowalut i zapisanie do tabel xxx_usd_historyczne
            saveCryptoRatesToUSD();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Funkcja pobierająca wszystkie dostępne waluty z API NBP
    public static List<Rates> getAllCurrenciesFromNBP() throws IOException {
        return Retriever.retrieveRates();
    }

    // Funkcja zapisująca kursy walut do tabeli kursy_fiat
    public static void saveFiatRates(List<Rates> ratesList) {
        DbService dbService = new DbService();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        String date = sdfDate.format(new Date());
        String time = sdfTime.format(new Date());

        for (Rates rate : ratesList) {
            try {
                dbService.insertFiatRate(date, time, rate.code(), rate.mid());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

