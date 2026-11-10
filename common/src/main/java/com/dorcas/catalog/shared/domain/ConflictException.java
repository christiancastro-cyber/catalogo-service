package com.dorcas.catalog.shared.domain;

public class ConflictException extends DorcasException {

    public ConflictException(ErrorCode code, String message) {
        super(code, message);
    }
}
