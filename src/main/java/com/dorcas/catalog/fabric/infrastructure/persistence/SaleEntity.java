package com.dorcas.catalog.fabric.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "sale")
class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fabric_id", nullable = false)
    private Long fabricId;

    private double meters;
    private double amount;
    private double remainingStock;

    protected SaleEntity(){}

    SaleEntity(Long id, Long fabricId, double meters, double amount, double remainingStock) {
        this.id = id;
        this.fabricId = fabricId;
        this.meters = meters;
        this.amount = amount;
        this.remainingStock = remainingStock;
    }

    Long getId() {
        return id;
    }

    Long getFabricId() {
        return fabricId;
    }

    double getMeters() {
        return meters;
    }

    double getAmount() {
        return amount;
    }

    double getRemainingStock() {
        return remainingStock;
    }
}
