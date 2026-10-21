package com.dorcas.catalogo.tela;

import jakarta.validation.constraints.*;

public record TelaInputDto(
        @NotBlank @Size(max = 20) String referencia,
        @NotBlank @Size(max = 80) String nombre,
        @NotNull Categoria categoria,
        @NotNull @Positive Double precioMetro,
        @NotNull @PositiveOrZero Double stockMetros,
        @NotNull Boolean aptaPrincipiantes
) {}
