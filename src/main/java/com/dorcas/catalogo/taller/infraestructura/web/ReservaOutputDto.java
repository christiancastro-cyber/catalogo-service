package com.dorcas.catalogo.taller.infraestructura.web;

import com.dorcas.catalogo.taller.dominio.Reserva;

public record ReservaOutputDto(
        Long id,
        Long tallerId,
        String alumna
) {

    public static ReservaOutputDto desde(Long tallerId, Reserva reserva) {
        return new ReservaOutputDto(
                reserva.getId(),
                tallerId,
                reserva.getAlumna()
        );
    }
}
