package com.dorcas.catalogo.compartido;

public class RecursoNoEncontradoException extends DorcasException {
    public RecursoNoEncontradoException(String codigo, String message) {
        super(codigo, message);
    }
}
