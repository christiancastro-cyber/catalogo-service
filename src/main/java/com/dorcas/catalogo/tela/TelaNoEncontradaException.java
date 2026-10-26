package com.dorcas.catalogo.tela;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TelaNoEncontradaException extends DorcasException {
    public TelaNoEncontradaException(Long id) {
        super("TELA_NO_ENCONTRADA","No existe la tela con id: " + id);
    }
}
