package com.dorcas.catalog.workshop.domain;

import com.dorcas.catalog.shared.domain.ConflictException;

public class KitFabricNotFoundException extends ConflictException {

    public KitFabricNotFoundException(Long fabricId) {
        super(WorkshopErrorCode.KIT_FABRIC_NOT_FOUND, "The kit fabric " + fabricId + " no longer exists in the catalog");
    }
}