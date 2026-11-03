package com.dorcas.catalogo.taller.infraestructura.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReservaInputDto(
        @NotBlank @Size(max = 80) String alumna
) {
}
