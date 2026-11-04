package com.dorcas.catalogo.tela.dominio;


import com.dorcas.catalogo.compartido.RecursoNoEncontradoException;

public class TelaNoEncontradaException extends RecursoNoEncontradoException {
    public TelaNoEncontradaException(Long id) {
        super("TELA_NO_ENCONTRADA","No existe la tela con id: " + id);
    }
}
