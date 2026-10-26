package com.dorcas.catalogo.compartido;

public class ConflictoException extends DorcasException {
    public ConflictoException(String codigo,String message) {
        super(codigo, message);
    }
}
