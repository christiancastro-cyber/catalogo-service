package com.dorcas.catalog.fabric.domain;

import com.dorcas.catalog.shared.domain.NotFoundException;

public class FabricNotFoundException extends NotFoundException {

    public FabricNotFoundException(Long id) {
        super(FabricErrorCode.FABRIC_NOT_FOUND, "Fabric not found with id " + id);
    }
}
