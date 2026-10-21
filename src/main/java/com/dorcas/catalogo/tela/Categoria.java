package com.dorcas.catalogo.tela;

public enum Categoria {
    MODA("Moda y confección"),
    FIESTA("Fiesta y disfraces"),
    INFANTIL("Infantil y canastilla"),
    HOGAR("Hogar y decoración"),
    MANUALIDADES("Manualidades"),
    ABRIGO("Abrigo");


    private final String etiqueta;

    Categoria(String etiqueta){
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta(){
        return etiqueta;
    }

}
