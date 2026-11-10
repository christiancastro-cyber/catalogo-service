package com.dorcas.catalog.workshop.infrastructure.persistence;

import com.dorcas.catalog.workshop.domain.Booking;
import com.dorcas.catalog.workshop.domain.Kit;
import com.dorcas.catalog.workshop.domain.Workshop;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

final class WorkshopMapper {

    private WorkshopMapper() {
    }

    static Workshop toDomain(WorkshopEntity entity) {
        List<Booking> bookings = entity.getBookings().stream()
                .map(b -> new Booking(b.getId(), b.getStudent()))
                .toList();
        Kit kit = (entity.getKitFabricId() == null)
                ? null
                : new Kit(entity.getKitFabricId(), entity.getKitMeters());
        return new Workshop(entity.getId(), entity.getName(), entity.getLevel(),
                entity.getSeats(), entity.getPrice(), kit, bookings);
    }

    static WorkshopEntity toNewEntity(Workshop workshop) {
        Long kitFabricId = workshop.getKit().map(Kit::fabricId).orElse(null);
        Double kitMeters = workshop.getKit().map(Kit::meters).orElse(null);
        WorkshopEntity entity = new WorkshopEntity(workshop.getName(), workshop.getLevel(),
                workshop.getSeats(), workshop.getPrice(), kitFabricId, kitMeters);
        copyBookings(workshop, entity);
        return entity;
    }

    static void copyBookings(Workshop workshop, WorkshopEntity entity) {
        Set<Long> keptIds = workshop.getBookings().stream()
                .map(Booking::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        entity.getBookings().removeIf(b -> !keptIds.contains(b.getId()));

        workshop.getBookings().stream()
                .filter(b -> b.getId() == null)
                .forEach(b -> entity.getBookings().add(new BookingEntity(entity, b.getStudent())));
    }
}