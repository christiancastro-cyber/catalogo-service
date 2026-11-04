package com.dorcas.catalog.workshop.domain;

import com.dorcas.catalog.shared.domain.ConflictException;
import com.dorcas.catalog.shared.domain.ErrorCode;

public class WorkshopFullException extends ConflictException {

    public WorkshopFullException(String workshop, int seats) {
        super(ErrorCode.WORKSHOP_FULL, workshop + " is full (" + seats + " seats)");
    }
}
