package com.dorcas.catalogo.taller;

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
