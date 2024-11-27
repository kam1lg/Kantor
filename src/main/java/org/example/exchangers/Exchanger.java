package org.example.exchangers;

import org.example.models.Rates;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.decimal4j.util.DoubleRounder;

public class Exchanger {
    private final List<Rates> ratesList;
    private final Map<String, Double> ratesMap = new HashMap<>();

    public Exchanger(List<Rates> ratesList) {
        this.ratesList = ratesList;
        for (Rates rates : ratesList) {
            ratesMap.put(rates.code(), rates.mid());
        }
    }

    public void showRates() {
        ratesList.forEach(System.out::println);
    }

    public Result buyCurrency(Scanner scanner) {
        double rate;
        double exchanged;
        String code;
        double amount;
        String action = "buy";

        while (true) {
            System.out.println("Please enter the currency code you would like to buy: ");
            code = scanner.nextLine().toUpperCase();
            if (ratesMap.containsKey(code)) {
                rate = ratesMap.get(code);
                break;
            } else {
                System.out.println("Invalid currency code");
            }
        }
        while (true) {
            System.out.println("Please enter the amount of PLN you would like to use for the purchase: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid amount. Please enter a valid number.");
                scanner.next();
            }
            amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero. Please try again.");
            } else {
                break;
            }

        }

        exchanged = DoubleRounder.round(amount / rate, 2);
        return new Result(code, amount, exchanged, action);
    }

    public Result sellCurrency(Scanner scanner) {
        double rate = 0;
        double exchanged;
        String code;
        double amount;
        String action = "sell";
        while (true) {
            System.out.println("Please enter the currency code you would like to sell: ");
            code = scanner.nextLine().toUpperCase();

            if (ratesMap.containsKey(code)) {
                rate = ratesMap.get(code);
                break;
            } else {
                System.out.println("Invalid currency code");
            }
        }

        while (true) {
            System.out.println("Please enter the amount of " + code + " you would like to sell: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid amount. Please enter a valid number.");
                scanner.next();
            }
            amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero. Please try again.");
            } else {
                break;
            }

        }

        exchanged = DoubleRounder.round(amount * rate, 2);
        return new Result(code, amount, exchanged, action);
    }

    public Result buyCurrency(String currencyCode, double amount) {
        double rate;
        double exchanged;
        String code = currencyCode.toUpperCase();
        String action = "buy";

        if (ratesMap.containsKey(code)) {
            rate = ratesMap.get(code);
        } else {
            System.out.println("Invalid currency code");
            return null;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return null;
        }

        exchanged = DoubleRounder.round(amount / rate, 2);
        return new Result(code, amount, exchanged, action);
    }

    public Result sellCurrency(String currencyCode, double amount) {
        double rate;
        double exchanged;
        String action = "sell";
        String code = currencyCode.toUpperCase();

        if (ratesMap.containsKey(code)) {
            rate = ratesMap.get(code);
        } else {
            System.out.println("Invalid currency code");
            return null;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return null;
        }

        exchanged = DoubleRounder.round(rate * amount, 2);
        return new Result(code, amount, exchanged, action);
    }

}




