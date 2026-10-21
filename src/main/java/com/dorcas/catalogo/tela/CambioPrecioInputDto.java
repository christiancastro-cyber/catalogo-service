package com.dorcas.catalogo.tela;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record CambioPrecioInputDto(
        @NotNull @Positive Double precioMetro) {}
