package com.dorcas.catalog.workshop.domain;

public record KitDetails(Long fabricId, String fabricName, double meters,
                         double pricePerMeter, boolean inStock) {

    public static KitDetails of(Kit kit, FabricInfo fabric) {
        boolean inStock = fabric.stockMeters() >= kit.meters();
        return new KitDetails(fabric.id(), fabric.name(), kit.meters(), fabric.pricePerMeter(), inStock);
    }
}