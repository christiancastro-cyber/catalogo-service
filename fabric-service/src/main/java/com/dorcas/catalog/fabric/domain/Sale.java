package com.dorcas.catalog.fabric.domain;


public class Sale {

    private final Long id;
    private final Long fabricId;
    private final double meters;
    private final double amount;
    private final double remainingStock;

    public Sale(Long fabricId, double meters, double amount, double remainingStock) {
        this(null, fabricId, meters, amount, remainingStock);
    }

    public Sale(Long id, Long fabricId, double meters, double amount, double remainingStock) {
        this.id = id;
        this.fabricId = fabricId;
        this.meters = meters;
        this.amount = amount;
        this.remainingStock = remainingStock;
    }

    public Long getId() {
        return id;
    }

    public Long getFabricId() {
        return fabricId;
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
