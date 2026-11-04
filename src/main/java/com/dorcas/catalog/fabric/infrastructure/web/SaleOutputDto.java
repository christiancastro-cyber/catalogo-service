package com.dorcas.catalog.fabric.infrastructure.web;

import com.dorcas.catalog.fabric.domain.Sale;

public record SaleOutputDto(Long id, Long fabricId, double meters, double amount, double remainingStock) {

    public static SaleOutputDto from(Sale sale) {
        return new SaleOutputDto(
                sale.getId(),
                sale.getFabric().getId(),
                sale.getMeters(),
                sale.getAmount(),
                sale.getRemainingStock());
    }
}
