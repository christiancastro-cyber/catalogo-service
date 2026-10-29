package com.dorcas.catalogo.tela;

import jakarta.persistence.*;

@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tela_id")
    private Tela tela;

    private double metros;
    private double importe;
    private double stockRestante;

    protected Venta(){}

    public Venta(Tela tela, double metros, double importe, double stockRestante) {
        this.tela = tela;
        this.metros = metros;
        this.importe = importe;
        this.stockRestante = stockRestante;
    }

    public long getId() {
        return id;
    }

    public Tela getTela() {
        return tela;
    }

    public double getMetros() {
        return metros;
    }

    public double getImporte() {
        return importe;
    }

    public double getStockRestante() {
        return stockRestante;
    }
}
