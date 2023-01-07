package com.bbva.pisd.lib.r103.impl.utils;

public enum Properties {
    QUERY_INSERT_INSURANCE_CNCL_REQUEST("PISD.INSERT_INSURANCE_CANCELLATION_REQUEST"),
    QUERY_SELECT_INSURANCE_CNCL_REQUEST("PISD.SELECT_INSURANCE_CANCELLATION_REQUEST");
    private final String value;

    public String getValue() {
        return this.value;
    }

    private Properties(String value) { this.value = value; }
}
