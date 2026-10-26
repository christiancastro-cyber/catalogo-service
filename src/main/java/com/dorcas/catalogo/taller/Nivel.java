package com.dorcas.catalogo.taller;

public enum Nivel {
    INICIACION("Para principiantes"),
    INTERMEDIO("Para aquell@s que saben coser"),
    AVANZADO("Para aquell@s que no están conformes con saber mucho");

    private final String etiqueta;

    Nivel(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
