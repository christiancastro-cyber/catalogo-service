package com.dorcas.catalog.fabric.domain;

import com.dorcas.catalog.shared.domain.ConflictException;

public class DuplicateReferenceException extends ConflictException {

    public DuplicateReferenceException(String reference) {
        super(FabricErrorCode.DUPLICATE_REFERENCE, "A fabric with reference " + reference + " already exists");
    }
}
