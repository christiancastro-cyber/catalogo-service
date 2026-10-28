package com.dorcas.catalogo.tela;

import com.dorcas.catalogo.compartido.ConflictoException;

public class TelaConVentasException extends ConflictoException {
    public TelaConVentasException(Long id) {

        super("TELA_CON_VENTAS", "La tela " + id + " tiene ventas registradas y no se puede borrar");
    }
}
