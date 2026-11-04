package com.dorcas.catalog.workshop.domain;

import com.dorcas.catalog.shared.domain.NotFoundException;
import com.dorcas.catalog.shared.domain.ErrorCode;

public class WorkshopNotFoundException extends NotFoundException {

    public WorkshopNotFoundException(Long id) {
        super(ErrorCode.WORKSHOP_NOT_FOUND, "Workshop not found with id " + id);
    }
}
