package com.dorcas.catalog.workshop.domain;

public enum Level {
    BEGINNER("For beginners"),
    INTERMEDIATE("For those who already know how to sew"),
    ADVANCED("For those who always want to learn more");

    private final String label;

    Level(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
