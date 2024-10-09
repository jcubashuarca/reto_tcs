package com.example.demo;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrencyExchangeService {

    private final ExchangeRateClient exchangeRateClient;
    private final CurrencyExchangeRepository repository;

    public CurrencyExchangeService(ExchangeRateClient exchangeRateClient, CurrencyExchangeRepository repository) {
        this.exchangeRateClient = exchangeRateClient;
        this.repository = repository;
    }

    public Mono<CurrencyExchange> applyExchangeRate(String sourceCurrency, String targetCurrency, double amount) {
        return exchangeRateClient.getExchangeRate(sourceCurrency)
                .flatMap(response -> {
                    Double rate = response.getRates().get(targetCurrency);
                    if (rate == null) {
                        return Mono.error(new RuntimeException("Exchange rate not found"));
                    }
                    double convertedAmount = amount * rate;
                    CurrencyExchange exchange = new CurrencyExchange(sourceCurrency, targetCurrency, amount, rate, convertedAmount);
                    repository.save(exchange);
                    return Mono.just(exchange);
                });
    }

    public Flux<CurrencyExchange> getAllExchanges() {
        return Flux.fromIterable(repository.findAll());
    }
}
