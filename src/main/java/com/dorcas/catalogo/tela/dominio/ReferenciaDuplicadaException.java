package com.dorcas.catalogo.tela.dominio;

import com.dorcas.catalogo.compartido.ConflictoException;

public class ReferenciaDuplicadaException extends ConflictoException {
    public ReferenciaDuplicadaException(String referencia) {
        super("REFERENCIA_DUPLICADA", "Ya existe una tela con la referencia: "+ referencia);
    }
}
