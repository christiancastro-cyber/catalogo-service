package com.dorcas.catalogo.tela;

public class DorcasException extends RuntimeException {
    private final String codigo;

    public DorcasException(String codigo, String message) {

        super(message);
        this.codigo = codigo;
    }

    public String getCodigo(){
        return codigo;
    }
}
