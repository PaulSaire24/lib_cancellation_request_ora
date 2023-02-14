package com.bbva.pisd.lib.r103.impl.utils;

public enum CatalogEnum {
    ESTADO_DE_POLIZA("0011"),
    TIPO_DE_CONTACTO("0012"),
    TIPO_DE_UNIDAD("0013"),
    FRECUENCIA_DE_PAGO("0014");
    private final String value;
    CatalogEnum(String value) {this.value = value;}
    public String getValue() { return value; }
}
