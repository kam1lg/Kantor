package org.example.exchangers;

public record Result(String code, double amount, double exchanged, String action) {

    @Override
    public String toString() {
        if (action.equals("buy")) {
            return "For " + amount + " PLN you will get: " + exchanged + " " + code;
        }
        return "For " + amount + " " + code + " you will get: " + exchanged + " PLN";
    }
}
