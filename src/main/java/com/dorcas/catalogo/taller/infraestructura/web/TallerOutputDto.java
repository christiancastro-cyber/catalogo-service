package com.dorcas.catalogo.taller.infraestructura.web;

import com.dorcas.catalogo.taller.dominio.Nivel;
import com.dorcas.catalogo.taller.dominio.Taller;

public record TallerOutputDto(
        Long id,
        String nombre,
        Nivel nivel,
        int plazas,
        int plazasLibres,
        double precio
) {

    public static TallerOutputDto desde(Taller taller) {
        return new TallerOutputDto(
                taller.getId(),
                taller.getNombre(),
                taller.getNivel(),
                taller.getPlazas(),
                taller.plazasLibres(),
                taller.getPrecio());
    }

}
