package com.dorcas.catalogo.tela;


public class TelaNoEncontradaException extends DorcasException {
    public TelaNoEncontradaException(Long id) {
        super("TELA_NO_ENCONTRADA","No existe la tela con id: " + id);
    }
}
