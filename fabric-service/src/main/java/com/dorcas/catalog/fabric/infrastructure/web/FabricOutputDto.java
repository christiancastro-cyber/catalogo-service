package com.dorcas.catalog.fabric.infrastructure.web;

import com.dorcas.catalog.fabric.domain.Category;
import com.dorcas.catalog.fabric.domain.Fabric;

public record FabricOutputDto(
        Long id,
        String reference,
        String name,
        Category category,
        double pricePerMeter,
        double stockMeters,
        boolean beginnerFriendly
) {

    public static FabricOutputDto from(Fabric fabric) {
        return new FabricOutputDto(
                fabric.getId(),
                fabric.getReference(),
                fabric.getName(),
                fabric.getCategory(),
                fabric.getPricePerMeter(),
                fabric.getStockMeters(),
                fabric.isBeginnerFriendly());
    }
}
