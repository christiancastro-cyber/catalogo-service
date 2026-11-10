package com.dorcas.catalog.workshop.domain;

import com.dorcas.catalog.shared.domain.NotFoundException;

public class WorkshopNotFoundException extends NotFoundException {

    public WorkshopNotFoundException(Long id) {
        super(WorkshopErrorCode.WORKSHOP_NOT_FOUND, "Workshop not found with id " + id);
    }
}
