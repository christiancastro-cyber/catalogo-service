package com.dorcas.catalog.fabric.domain;

import com.dorcas.catalog.shared.domain.ErrorCode;

public enum FabricErrorCode implements ErrorCode {
    FABRIC_NOT_FOUND,
    DUPLICATE_REFERENCE,
    INSUFFICIENT_STOCK,
    FABRIC_HAS_SALES

}
