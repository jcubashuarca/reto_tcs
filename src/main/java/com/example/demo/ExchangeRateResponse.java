package com.example.demo;


import java.util.Map;

public class ExchangeRateResponse {
    private String result;
    private Map<String, Double> rates;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
