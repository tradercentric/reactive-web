package com.acme.webflux.tradefreeze;

import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.test.StepVerifier;

import javax.annotation.PostConstruct;

@Component
public class TradeFreezeLoader {

    final private DatabaseClient databaseClient;

    public TradeFreezeLoader(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @PostConstruct
    public void loadData() {

        databaseClient.execute("CREATE TABLE Trade_Freeze" +
                "(listing_Unique_Identifier INT PRIMARY KEY," +
                "freeze_Type_Code VARCHAR(255)," +
                "freeze_Indicator VARCHAR(1))")
                .fetch()
                .rowsUpdated()
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

        databaseClient.insert()
                .into(TradeFreeze.class)
                .using(new TradeFreeze(1L, "LEGL", "Y"))
                .then()
                .as(StepVerifier::create)
                .verifyComplete();

        databaseClient.insert()
                .into(TradeFreeze.class)
                .using(new TradeFreeze(2L, "RPTG", "Y"))
                .then()
                .as(StepVerifier::create)
                .verifyComplete();

        databaseClient.insert()
                .into(TradeFreeze.class)
                .using(new TradeFreeze(3L, "SAD", "Y"))
                .then()
                .as(StepVerifier::create)
                .verifyComplete();

        databaseClient.insert()
                .into(TradeFreeze.class)
                .using(new TradeFreeze(4L, "SAD", "Y"))
                .then()
                .as(StepVerifier::create)
                .verifyComplete();
    }
}
