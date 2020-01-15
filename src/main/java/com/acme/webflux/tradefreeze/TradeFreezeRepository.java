package com.acme.webflux.tradefreeze;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Flux;

public interface TradeFreezeRepository extends CrudRepository<TradeFreeze, Long> {

    @Query("SELECT f.* FROM Trade_Freeze f")
    Flux<TradeFreeze> all();

    @Query("SELECT f.* FROM Trade_Freeze f WHERE f.listing_Unique_Identifier = :listingUniqueIdentifier")
    Flux<TradeFreeze> get(Long listingUniqueIdentifier);

    @Query("SELECT f.* FROM Trade_Freeze f WHERE f.freeze_Type_Code LIKE :freezeTypeCode")
    Flux<TradeFreeze> getType(String freezeTypeCode);
}
