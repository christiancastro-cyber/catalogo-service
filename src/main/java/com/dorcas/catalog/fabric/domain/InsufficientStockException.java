package com.dorcas.catalog.fabric.domain;

import com.dorcas.catalog.shared.domain.ConflictException;
import com.dorcas.catalog.shared.domain.ErrorCode;

public class InsufficientStockException extends ConflictException {

    public InsufficientStockException(String fabric, double requested, double available) {
        super(ErrorCode.INSUFFICIENT_STOCK, "Not enough " + fabric + ": requested " + requested + " m, only " + available + " m left");
    }
}
