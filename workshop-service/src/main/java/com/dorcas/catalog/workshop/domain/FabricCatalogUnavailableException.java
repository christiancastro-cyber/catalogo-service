package com.dorcas.catalog.workshop.domain;

import com.dorcas.catalog.shared.domain.ServiceUnavailableException;

public class FabricCatalogUnavailableException extends ServiceUnavailableException {

    public FabricCatalogUnavailableException() {
        super(WorkshopErrorCode.FABRIC_CATALOG_UNAVAILABLE,
                "The fabric catalog is not available right now. Please try again in a few seconds");
    }
}