package com.dorcas.catalogo.taller;

import com.dorcas.catalogo.compartido.RecursoNoEncontradoException;

public class TallerNoEncontradoException extends RecursoNoEncontradoException {
    public TallerNoEncontradoException(Long id) {
        super("TALLER_NO_ENCONTRADO", "'No existe el taller con id " + id + "'");
    }
}
