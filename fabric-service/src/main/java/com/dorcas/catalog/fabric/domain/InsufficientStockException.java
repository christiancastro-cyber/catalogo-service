package com.dorcas.catalog.fabric.domain;

import com.dorcas.catalog.shared.domain.ConflictException;

public class InsufficientStockException extends ConflictException {

    public InsufficientStockException(String fabric, double requested, double available) {
        super(FabricErrorCode.INSUFFICIENT_STOCK, "Not enough " + fabric + ": requested " + requested + " m, only " + available + " m left");
    }
}
