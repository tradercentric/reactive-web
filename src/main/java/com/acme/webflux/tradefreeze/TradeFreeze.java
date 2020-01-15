package com.acme.webflux.tradefreeze;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class TradeFreeze {

    @Id
    public Long listingUniqueIdentifier;
    public String freezeTypeCode;
    public String freezeIndicator = "Y";

    public TradeFreeze() {
    }

    public TradeFreeze(Long listingUniqueIdentifier, String freezeTypeCode, String freezeIndicator) {
        this.listingUniqueIdentifier = listingUniqueIdentifier;
        this.freezeTypeCode = freezeTypeCode;
        this.freezeIndicator = freezeIndicator;
    }

    public Long getListingUniqueIdentifier() {
        return listingUniqueIdentifier;
    }

    public void setListingUniqueIdentifier(Long listingUniqueIdentifier) {
        this.listingUniqueIdentifier = listingUniqueIdentifier;
    }

    public String getFreezeTypeCode() {
        return freezeTypeCode;
    }

    public void setFreezeTypeCode(String freezeTypeCode) {
        this.freezeTypeCode = freezeTypeCode;
    }

    public String getFreezeIndicator() {
        return freezeIndicator;
    }

    public void setFreezeIndicator(String freezeIndicator) {
        this.freezeIndicator = freezeIndicator;
    }

    @Override
    public String toString() {
        return "TradeFreeze{" +
                "listingUniqueIdentifier=" + listingUniqueIdentifier +
                ", freezeTypeCode='" + freezeTypeCode + '\'' +
                ", freezeIndicator='" + freezeIndicator + '\'' +
                '}';
    }
}
