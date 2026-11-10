package com.dorcas.catalog.shared.domain;

public class NotFoundException extends DorcasException {

    public NotFoundException(ErrorCode code, String message) {
        super(code, message);
    }
}
