package com.dev.diego.backend.products;

public enum Category {
    ELETRONICO("Eletrônico"),
    ROUPA("Roupa"),
    ESCRITORIO("Escritório"),
    MOVEIS("Móveis");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getVale() {
        return this.value;
    }
}
