package com.dorcas.catalog.shared.domain;

public class ServiceUnavailableException extends DorcasException{
    public ServiceUnavailableException(ErrorCode code, String message){
        super(code,message);
    }
}
