package com.dorcas.catalog.fabric.infrastructure.persistence;

import com.dorcas.catalog.fabric.domain.Category;
import jakarta.persistence.*;

@Entity
@Table(name= "fabric")
class FabricEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String reference;

    @Column(nullable = false, length = 80)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    private double pricePerMeter;
    private double stockMeters;
    private boolean beginnerFriendly;

    protected FabricEntity(){

    }

    FabricEntity(Long id, String reference, String name, Category category, double pricePerMeter, double stockMeters, boolean beginnerFriendly) {
        this.id = id;
        this.reference = reference;
        this.name = name;
        this.category = category;
        this.pricePerMeter = pricePerMeter;
        this.stockMeters = stockMeters;
        this.beginnerFriendly = beginnerFriendly;
    }

    Long getId() {
        return id;
    }

    String getReference() {
        return reference;
    }

    String getName() {
        return name;
    }

    Category getCategory() {
        return category;
    }

    double getPricePerMeter() {
        return pricePerMeter;
    }

    double getStockMeters() {
        return stockMeters;
    }

    boolean isBeginnerFriendly() {
        return beginnerFriendly;
    }
}
