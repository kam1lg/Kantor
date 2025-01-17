package org.example.service;

import org.example.models.Rates;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class FiatRatesTask extends TimerTask {
    @Override
    public void run() {
        try {
            // Pobranie wszystkich walut z NBP
            List<Rates> ratesList = getAllCurrenciesFromNBP();

            // Zapisanie kursów walut do tabeli kursy_fiat
            saveFiatRates(ratesList);
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
}
