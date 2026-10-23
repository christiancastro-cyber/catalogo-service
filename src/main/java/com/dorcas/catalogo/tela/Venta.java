package com.dorcas.catalogo.tela;

public class Venta {
    private final Long id;
    private double metros;

    public Venta(Long id, double metros) {
        this.id = id;
        this.metros = metros;
    }

    public Long getId() {
        return id;
    }

    public Double metros(){
        return metros;
    }
}
