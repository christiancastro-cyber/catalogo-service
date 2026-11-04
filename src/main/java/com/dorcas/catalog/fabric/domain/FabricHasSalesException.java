package com.dorcas.catalog.fabric.domain;

import com.dorcas.catalog.shared.domain.ConflictException;
import com.dorcas.catalog.shared.domain.ErrorCode;

public class FabricHasSalesException extends ConflictException {

    public FabricHasSalesException(Long id) {
        super(ErrorCode.FABRIC_HAS_SALES, "Fabric " + id + " has recorded sales and cannot be deleted");
    }
}
