package com.dorcas.catalog.workshop.domain;

import java.util.List;
import java.util.Optional;

public interface WorkshopRepository {

    List<Workshop> findAll();

    List<Workshop> findByLevel(Level level);

    Optional<Workshop> findById(Long id);

    Booking saveBooking(Booking booking);
}
