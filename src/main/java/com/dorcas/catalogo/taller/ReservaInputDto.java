package com.dorcas.catalogo.taller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReservaInputDto(
        @NotBlank @Size(max = 80) String nombre
){}
