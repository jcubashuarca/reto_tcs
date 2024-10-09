package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "exchangeRateClient", url = "https://open.er-api.com")
public interface ExchangeRateClient {
    @GetMapping("/v6/latest/{currency}")
    Mono<ExchangeRateResponse> getExchangeRate(@PathVariable("currency") String currency);
}