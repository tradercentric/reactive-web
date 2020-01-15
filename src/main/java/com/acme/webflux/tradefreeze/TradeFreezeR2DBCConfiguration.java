package com.acme.webflux.tradefreeze;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class TradeFreezeR2DBCConfiguration extends AbstractR2dbcConfiguration {

    public H2ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .url("mem:testdb;DB_CLOSE_DELAY=-1;TRACE_LEVEL_FILE=4")
                        .username("sa")
                        .build()
        );
    }

    @Bean
    public DatabaseClient getDatabaseClient() {
        return DatabaseClient.create(connectionFactory());
    }

//    @PostConstruct
//    public void loadData() {
//
//        Flux.from(connectionFactory().create())
//                .flatMap(c ->
//                        Flux.from(c.createBatch()
//                                .add("drop table if exists Trade_Freeze")
//                                .add("CREATE TABLE Trade_Freeze" +
//                                        "(listing_Unique_Identifier INT PRIMARY KEY," +
//                                        "freeze_Type_Code VARCHAR(255)," +
//                                        "freeze_Indicator VARCHAR(1))")
//                                .add("insert into Trade_Freeze(listing_Unique_Identifier,freeze_Type_Code,freeze_Indicator)" +
//                                        "values(5, 'LEGL', 'Y')")
//                                .add("insert into Trade_Freeze(listing_Unique_Identifier,freeze_Type_Code,freeze_Indicator)" +
//                                        "values(6, 'RPTG', 'Y')")
//                                .add("insert into Trade_Freeze(listing_Unique_Identifier,freeze_Type_Code,freeze_Indicator)" +
//                                        "values(7, 'SAD', 'Y')")
//                                .add("insert into Trade_Freeze(listing_Unique_Identifier,freeze_Type_Code,freeze_Indicator)" +
//                                        "values(8, 'SAD', 'Y')")
//                                .execute())
//                                .doFinally((st) -> c.close())
//                )
//                .log()
//                .blockLast();
//    }
}