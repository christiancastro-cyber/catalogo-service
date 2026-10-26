package com.dorcas.catalogo.taller;

import com.dorcas.catalogo.compartido.ConflictoException;

public class AlumnaYaApuntada extends ConflictoException {
    public AlumnaYaApuntada(String alumna, String taller) {
        super("ALUMNA_YA_APUNTADA", "' " +alumna + " ya está apuntada al "+ taller +"'");
    }
}
