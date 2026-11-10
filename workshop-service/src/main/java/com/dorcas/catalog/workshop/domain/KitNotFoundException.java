package com.dorcas.catalog.workshop.domain;

import com.dorcas.catalog.shared.domain.NotFoundException;

public class KitNotFoundException extends NotFoundException {

    public KitNotFoundException(Long workshopId) {
        super(WorkshopErrorCode.KIT_NOT_FOUND, "Workshop " + workshopId + " has no material kit");
    }
}