package com.bbva.pisd.lib.r103.impl.utils;

public enum Properties {

    QUERY_INSERT_INSURANCE_REQUEST_CNCL("PISD.INSERT_INSURANCE_REQUEST_CNCL"),
    QUERY_INSERT_INSURANCE_REQ_CNCL_MOV("PISD.INSERT_INSURANCE_REQ_CNCL_MOV"),
    QUERY_SELECT_REQUEST_SEQUENCE_ID("PISD.SELECT_REQUEST_SEQUENCE_ID"),
    QUERY_SELECT_INSURANCE_CNCL_REQUEST("PISD.SELECT_INSURANCE_CANCELLATION_REQUEST");

    private final String value;

    public String getValue() {
        return this.value;
    }

    private Properties(String value) { this.value = value; }
}
