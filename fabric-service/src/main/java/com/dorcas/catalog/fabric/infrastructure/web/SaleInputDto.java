package com.dorcas.catalog.fabric.infrastructure.web;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaleInputDto(@NotNull @Positive Double meters) {
}
