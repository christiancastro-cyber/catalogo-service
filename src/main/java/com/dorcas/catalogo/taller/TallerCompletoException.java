package com.dorcas.catalogo.taller;

import com.dorcas.catalogo.compartido.ConflictoException;

public class TallerCompletoException extends ConflictoException {
    public TallerCompletoException(String nombre, int plazas) {
        super("TALLER_COMPLETO", "El " + nombre + " está completo (" + plazas + " plazas) ");
    }
}
