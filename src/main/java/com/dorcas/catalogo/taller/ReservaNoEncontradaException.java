package com.dorcas.catalogo.taller;

import com.dorcas.catalogo.compartido.RecursoNoEncontradoException;

public class ReservaNoEncontradaException extends RecursoNoEncontradoException {
    public ReservaNoEncontradaException(Long tallerId, Long reservaId) {
        super("RESERVA_NO_ENCONTRADA", "No existe la reserva " + reservaId + " en el taller " + tallerId);
    }
}
