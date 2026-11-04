package com.dorcas.catalog.fabric.domain;

import com.dorcas.catalog.shared.domain.ConflictException;
import com.dorcas.catalog.shared.domain.ErrorCode;

public class DuplicateReferenceException extends ConflictException {

    public DuplicateReferenceException(String reference) {
        super(ErrorCode.DUPLICATE_REFERENCE, "A fabric with reference " + reference + " already exists");
    }
}
