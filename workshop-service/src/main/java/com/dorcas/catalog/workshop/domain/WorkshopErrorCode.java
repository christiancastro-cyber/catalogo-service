package com.dorcas.catalog.workshop.domain;

import com.dorcas.catalog.shared.domain.ErrorCode;

public enum WorkshopErrorCode implements ErrorCode {
    WORKSHOP_NOT_FOUND,
    BOOKING_NOT_FOUND,
    WORKSHOP_FULL,
    STUDENT_ALREADY_ENROLLED,
    KIT_NOT_FOUND,
    KIT_FABRIC_NOT_FOUND,
    FABRIC_CATALOG_UNAVAILABLE
}
