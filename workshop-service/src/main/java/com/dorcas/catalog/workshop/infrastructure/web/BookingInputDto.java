package com.dorcas.catalog.workshop.infrastructure.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookingInputDto(@NotBlank @Size(max = 80) String student) {
}
