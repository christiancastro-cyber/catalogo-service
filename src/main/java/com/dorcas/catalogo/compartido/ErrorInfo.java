package com.dorcas.catalogo.compartido;

import java.time.Instant;
import java.util.List;

public record ErrorInfo(
        int status,
        String error,
        String codigo,
        String mensaje,
        String ruta,
        Instant fecha,
        List<ErrorDetalle> detalles
) {
}
