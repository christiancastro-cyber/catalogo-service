package com.dorcas.catalogo.taller;

import com.dorcas.catalogo.compartido.ConflictoException;

public class AlumnaYaApuntadaException extends ConflictoException {
    public AlumnaYaApuntadaException(String alumna, String taller) {
        super("ALUMNA_YA_APUNTADA", alumna + " ya está apuntada al " + taller);
    }
}
