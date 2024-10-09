package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/exchange")
public class CurrencyExchangeController {

    private final CurrencyExchangeService service;

    public CurrencyExchangeController(CurrencyExchangeService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<ResponseEntity<CurrencyExchange>> exchange(
            @RequestParam String sourceCurrency,
            @RequestParam String targetCurrency,
            @RequestParam double amount) {
        return service.applyExchangeRate(sourceCurrency, targetCurrency, amount)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body(null)));
    }

    @GetMapping
    public Flux<CurrencyExchange> getAllExchanges() {
        return service.getAllExchanges();
    }
}

