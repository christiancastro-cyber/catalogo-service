package com.dorcas.catalogo.taller;

import com.dorcas.catalogo.tela.Tela;
import com.dorcas.catalogo.tela.TelaOutputDto;

public record ReservaOutputDto(
        Long id,
        Long tallerId,
        String alumna
) {

    public static ReservaOutputDto desde(Long tallerId, Reserva reserva){
        return new ReservaOutputDto(
                tallerId,
                reserva.id(),
                reserva.alumna()
        );
    }
}
