package org.example.utils;

import org.example.exchangers.Exchanger;
import org.example.models.Rates;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.example.utils.Retriever.retrieveRates;

public class Menu {
    private Scanner scanner;
    private Exchanger exchanger;
    List<Rates> rates = retrieveRates();

    public Menu() throws IOException {
        this.exchanger = new Exchanger(rates);
        this.scanner = new Scanner(System.in);
    }

    private void displayMenu() throws IOException {

        System.out.println("Currency Exchange Menu:");
        System.out.println("1. Show Rates");
        System.out.println("2. Buy Currency");
        System.out.println("3. Sell Currency");
        System.out.println("4. Exit");
        System.out.print("Please choose an option (1-4): ");
    }

    public void handleMenu() throws IOException {
        int choice;
        do {
            displayMenu();
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    exchanger.showRates();
                    break;
                case 2:
                    System.out.println(exchanger.buyCurrency(scanner).toString());
                    break;
                case 3:
                    System.out.println(exchanger.sellCurrency(scanner).toString());
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
            }
        } while (choice != 4);
        scanner.close();
    }
}
