package com.dorcas.catalog.workshop.infrastructure.web;

import com.dorcas.catalog.workshop.domain.KitDetails;

public record KitOutputDto(Long fabricId, String fabricName, double meters,
                           double pricePerMeter, boolean inStock) {

    public static KitOutputDto from(KitDetails kit) {
        return new KitOutputDto(kit.fabricId(), kit.fabricName(), kit.meters(),
                kit.pricePerMeter(), kit.inStock());
    }
}