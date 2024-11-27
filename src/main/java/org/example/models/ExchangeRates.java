package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExchangeRates(String table, String no, String effectiveDate, List<Rates> rates) {
}
