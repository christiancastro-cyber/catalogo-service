package com.dorcas.catalog.shared.domain;

public class DorcasException extends RuntimeException {

    private final ErrorCode code;

    public DorcasException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
