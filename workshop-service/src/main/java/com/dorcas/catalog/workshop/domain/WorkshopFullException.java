package com.dorcas.catalog.workshop.domain;

import com.dorcas.catalog.shared.domain.ConflictException;

public class WorkshopFullException extends ConflictException {

    public WorkshopFullException(String workshop, int seats) {
        super(WorkshopErrorCode.WORKSHOP_FULL, workshop + " is full (" + seats + " seats)");
    }
}
