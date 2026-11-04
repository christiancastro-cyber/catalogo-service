package com.dorcas.catalog.fabric.domain;

import jakarta.persistence.*;

@Entity
public class Fabric {

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

    protected Fabric() {
    }

    public Fabric(String reference, String name, Category category,
                  double pricePerMeter, double stockMeters, boolean beginnerFriendly) {
        this.reference = reference;
        this.name = name;
        this.category = category;
        this.pricePerMeter = pricePerMeter;
        this.stockMeters = stockMeters;
        this.beginnerFriendly = beginnerFriendly;
    }

    public double priceFor(double meters) {
        if (meters <= 0) {
            throw new IllegalArgumentException("Meters must be positive: " + meters);
        }
        double price = pricePerMeter * meters;
        if (meters >= 5) {
            price = price * 0.9;
        }
        return price;
    }

    public void sell(double meters) {
        if (meters <= 0) {
            throw new IllegalArgumentException("Meters must be positive: " + meters);
        }
        if (meters > stockMeters) {
            throw new InsufficientStockException(name, meters, stockMeters);
        }
        stockMeters -= meters;
    }

    public boolean needsRestock(double minimum) {
        return stockMeters < minimum;
    }

    public double stockValue() {
        return pricePerMeter * stockMeters;
    }

    public String careTip() {
        return switch (category) {
            case FASHION -> "Wash at 30 °C and iron inside out";
            case PARTY -> "Hand wash only, never tumble dry";
            case KIDS -> "Wash before sewing: it may shrink";
            case HOME -> "Can be washed at 40 °C";
            case CRAFTS -> "No special care needed";
            case OUTERWEAR -> "Dry cleaning recommended";
        };
    }

    public void update(String reference, String name, Category category,
                       double pricePerMeter, double stockMeters, boolean beginnerFriendly) {
        this.reference = reference;
        this.name = name;
        this.category = category;
        this.pricePerMeter = pricePerMeter;
        this.stockMeters = stockMeters;
        this.beginnerFriendly = beginnerFriendly;
    }

    public void changePrice(double newPrice) {
        if (newPrice <= 0) {
            throw new IllegalArgumentException("Price must be positive: " + newPrice);
        }
        this.pricePerMeter = newPrice;
    }

    public Long getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public double getPricePerMeter() {
        return pricePerMeter;
    }

    public double getStockMeters() {
        return stockMeters;
    }

    public boolean isBeginnerFriendly() {
        return beginnerFriendly;
    }

    @Override
    public String toString() {
        return name + " (" + category.getLabel() + "), " + String.format("%.2f", pricePerMeter)
                + " €/m, " + String.format("%.1f", stockMeters) + " m in stock";
    }
}
