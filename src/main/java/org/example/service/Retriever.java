package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.ExchangeRates;
import org.example.models.Rates;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Retriever {
    private Retriever(){}

    public static List<Rates> retrieveRates() throws IOException {
        List<Rates> ratesList = new ArrayList<>();
        int responseCode = 0;
        URL urlObj = new URL("https://api.nbp.pl/api/exchangerates/tables/A/?format=json");
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
        responseCode = connection.getResponseCode();
        if (responseCode != HttpsURLConnection.HTTP_OK) {
            System.out.println("Error in sending a GET request");
            return ratesList;
        }
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(connection.getInputStream());
        while (scanner.hasNext()) {
            sb.append(scanner.nextLine());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        List<ExchangeRates> exchangeRatesList = objectMapper.readValue(sb.toString(), new TypeReference<>() {
        });
        if (exchangeRatesList == null) {
            return ratesList;
        }
        return exchangeRatesList.get(0).rates();
    }
}
