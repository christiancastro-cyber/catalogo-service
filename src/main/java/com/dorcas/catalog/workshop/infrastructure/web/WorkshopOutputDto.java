package com.dorcas.catalog.workshop.infrastructure.web;

import com.dorcas.catalog.workshop.domain.Level;
import com.dorcas.catalog.workshop.domain.Workshop;

public record WorkshopOutputDto(
        Long id,
        String name,
        Level level,
        int seats,
        int freeSeats,
        double price
) {

    public static WorkshopOutputDto from(Workshop workshop) {
        return new WorkshopOutputDto(
                workshop.getId(),
                workshop.getName(),
                workshop.getLevel(),
                workshop.getSeats(),
                workshop.freeSeats(),
                workshop.getPrice());
    }
}
