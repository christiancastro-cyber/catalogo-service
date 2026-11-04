package com.dorcas.catalog.fabric.domain;

import jakarta.persistence.*;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fabric_id")
    private Fabric fabric;

    private double meters;
    private double amount;
    private double remainingStock;

    protected Sale() {
    }

    public Sale(Fabric fabric, double meters, double amount, double remainingStock) {
        this.fabric = fabric;
        this.meters = meters;
        this.amount = amount;
        this.remainingStock = remainingStock;
    }

    public Long getId() {
        return id;
    }

    public Fabric getFabric() {
        return fabric;
    }

    public double getMeters() {
        return meters;
    }

    public double getAmount() {
        return amount;
    }

    public double getRemainingStock() {
        return remainingStock;
    }
}
