package org.example;

import org.example.service.FiatRatesTask;
import org.example.service.CryptoRatesTask;

import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        Timer timer = new Timer();

        // Uruchomienie zadania Task co godzinę (3600000 ms = 60 minut)
        timer.schedule(new CryptoRatesTask(), 0, 3600000);

        // Uruchomienie zadania RatesTask co 24 godziny (86400000 ms = 24 godziny)
        timer.schedule(new FiatRatesTask(), 0, 86400000);
    }
}
