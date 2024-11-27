package org.example;

import org.example.exchangers.Exchanger;
import org.example.exchangers.Result;
import org.example.models.Rates;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


public class ExchangerTest {
    List<Rates> rates = List.of(new Rates("Dolar Amerykanski", "USD", 4.2), new Rates("Forint", "HUF", 0.011));
    Exchanger exchanger = new Exchanger(rates);

    @Test
    public void validAmountBuyTest() {
        assertEquals(952.38, exchanger.buyCurrency("USD", 4000).exchanged());
    }

    @Test
    public void invalidAmountBuyTest() {
        assertNull(exchanger.buyCurrency("USD", -4000));
    }

    @Test
    public void invalidCodeBuyTest() {
        assertNull(exchanger.buyCurrency("PLN", -4000));
    }

    @Test
    public void validAmountSellTest() {
        assertEquals(420, exchanger.sellCurrency("USD", 100).exchanged());
    }

    @Test
    public void invalidAmountSellTest() {
        assertNull(exchanger.sellCurrency("USD", -100));
    }

    @Test
    public void invalidCodeSellTest() {
        assertNull(exchanger.sellCurrency("PLN", 100));
    }

    @Test
    public void validInputAmountBuyRateTest() {
        String simulatedInput = "USD\n4000\n";
        Scanner scanner = new Scanner(simulatedInput);
        assertEquals(952.38, exchanger.buyCurrency(scanner).exchanged());
    }

    @Test
    public void invalidInputAmountBuyRateTest() {
        String simulatedInput = "ABC\nUSD\n4000\n";
        Scanner scanner = new Scanner(simulatedInput);

        Result result = exchanger.buyCurrency(scanner);
        assertNotNull(result);
        assertEquals("USD", result.code());
        assertEquals(4000, result.amount(), 0.0);
        assertEquals(952.38, result.exchanged(), 0.01);
    }

    @Test
    public void validInputAmountSellRateTest() {
        String simulatedInput = "USD\n100\n";
        Scanner scanner = new Scanner(simulatedInput);
        assertEquals(420, exchanger.sellCurrency(scanner).exchanged());
    }

    @Test
    public void invalidInputAmountSellRateTest() {
        String simulatedInput = "ABC\nUSD\n50\n";
        Scanner scanner = new Scanner(simulatedInput);

        Result result = exchanger.sellCurrency(scanner);
        assertNotNull(result);
        assertEquals("USD", result.code());
        assertEquals(50, result.amount(), 0.0);
        assertEquals(210.0, result.exchanged(), 0.01);
    }

}
