package org.example.utils;

public class HelpGuide {
    private HelpGuide() {
    }

    public static void printGuide() {
        System.out.println("Usage: <command> [args]");
        System.out.println("Commands:");
        System.out.println("help - show help");
        System.out.println("rates - show rates");
        System.out.println("menu - Starts program with selection menu");
        System.out.println("buy <code> <amount> - buy currency");
        System.out.println("sell <code> <amount> - sell currency");
    }
}
