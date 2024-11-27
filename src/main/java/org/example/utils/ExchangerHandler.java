package org.example.utils;

import org.example.exchangers.Exchanger;

import java.io.IOException;
import java.util.Objects;

import static org.example.utils.HelpGuide.printGuide;

public class ExchangerHandler {
    private final Exchanger exchanger;

    public ExchangerHandler(Exchanger exchanger) throws IOException {
        this.exchanger = Objects.requireNonNull(exchanger);
    }

    public void handleExchanger(String[] args) throws IOException {
        if (args.length == 0) {
            printGuide();
        } else if (Objects.equals(args[0], "help")) {
            printGuide();
        } else if (Objects.equals(args[0], "rates")) {
            exchanger.showRates();
        } else if (Objects.equals(args[0], "menu")) {
            Menu menu = new Menu();
            menu.handleMenu();
        } else if (Objects.equals(args[0], "buy") && args.length == 3) {
            String code = args[1];
            double amount = Double.parseDouble(args[2]);
            System.out.println(exchanger.buyCurrency(code, amount));
        } else if (Objects.equals(args[0], "sell") && args.length == 3) {
            String code = args[1];
            double amount = Double.parseDouble(args[2]);
            System.out.println(exchanger.sellCurrency(code, amount));
        } else {
            printGuide();
        }
    }
}

