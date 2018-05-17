package com.example.emina.dva232_assignment2;


import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Emina on 21-Dec-17.
 */

public class CurrencyConversionResponse {

    private String base;
    private String date;

    private Map<String, String> rates = new TreeMap<String, String>();

    public Map<String, String> getRates() {
        return rates;
    }

    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}