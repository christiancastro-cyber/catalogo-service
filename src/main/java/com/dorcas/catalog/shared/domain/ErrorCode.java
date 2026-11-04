package com.dorcas.catalog.shared.domain;

public enum ErrorCode {

    // Generic request errors
    INVALID_DATA,
    INVALID_JSON,
    INVALID_PARAMETER,

    // Fabrics
    FABRIC_NOT_FOUND,
    DUPLICATE_REFERENCE,
    INSUFFICIENT_STOCK,
    FABRIC_HAS_SALES,

    // Workshops
    WORKSHOP_NOT_FOUND,
    BOOKING_NOT_FOUND,
    WORKSHOP_FULL,
    STUDENT_ALREADY_ENROLLED
}
