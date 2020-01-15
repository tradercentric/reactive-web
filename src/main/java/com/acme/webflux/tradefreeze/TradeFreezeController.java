package com.acme.webflux.tradefreeze;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class TradeFreezeController {

    private final TradeFreezeRepository repository;

    public TradeFreezeController(TradeFreezeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tradeFreezes")
    public Flux<TradeFreeze> all() {
        return repository.all();
    }

    @GetMapping("/tradeFreeze/{id}")
    public Flux<TradeFreeze> get(@PathVariable Long id) {
        return repository.get(id);
    }

    @GetMapping("/tradeFreeze/type/{type}")
    public Flux<TradeFreeze> get(@PathVariable String type) {
        return repository.getType(type);
    }
}