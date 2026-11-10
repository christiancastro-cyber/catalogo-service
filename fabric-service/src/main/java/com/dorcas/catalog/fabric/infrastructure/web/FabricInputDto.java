package com.dorcas.catalog.fabric.infrastructure.web;

import com.dorcas.catalog.fabric.domain.Category;
import com.dorcas.catalog.fabric.domain.Fabric;
import jakarta.validation.constraints.*;

public record FabricInputDto(
        @NotBlank @Size(max = 20) String reference,
        @NotBlank @Size(max = 80) String name,
        @NotNull Category category,
        @NotNull @Positive Double pricePerMeter,
        @NotNull @PositiveOrZero Double stockMeters,
        @NotNull Boolean beginnerFriendly
) {

    public Fabric toFabric() {
        return new Fabric(reference, name, category, pricePerMeter, stockMeters, beginnerFriendly);
    }
}
