package com.dorcas.catalog.fabric.domain;

import com.dorcas.catalog.shared.domain.NotFoundException;
import com.dorcas.catalog.shared.domain.ErrorCode;

public class FabricNotFoundException extends NotFoundException {

    public FabricNotFoundException(Long id) {
        super(ErrorCode.FABRIC_NOT_FOUND, "Fabric not found with id " + id);
    }
}
