package com.dorcas.catalog.fabric.domain;

public enum Category {
    FASHION("Fashion and dressmaking"),
    PARTY("Party and costumes"),
    KIDS("Kids and baby"),
    HOME("Home and decor"),
    CRAFTS("Crafts"),
    OUTERWEAR("Outerwear");

    private final String label;

    Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
