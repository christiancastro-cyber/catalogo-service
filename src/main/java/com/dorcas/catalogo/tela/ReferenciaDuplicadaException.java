package com.dorcas.catalogo.tela;

public class ReferenciaDuplicadaException extends DorcasException {
    public ReferenciaDuplicadaException(String referencia) {
        super("REFERENCIA_DUPLICADA", "Ya existe una tela con la referencia: "+ referencia);
    }
}
