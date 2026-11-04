package com.dorcas.catalog.workshop.infrastructure.persistence;

import com.dorcas.catalog.workshop.domain.Booking;
import com.dorcas.catalog.workshop.domain.Level;
import com.dorcas.catalog.workshop.domain.Workshop;
import com.dorcas.catalog.workshop.domain.WorkshopRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class WorkshopRepositoryJpa implements WorkshopRepository {

    private final WorkshopJpaRepository workshops;
    private final BookingJpaRepository bookings;

    WorkshopRepositoryJpa(WorkshopJpaRepository workshops, BookingJpaRepository bookings) {
        this.workshops = workshops;
        this.bookings = bookings;
    }

    @Override
    public List<Workshop> findAll() {
        return workshops.findAll();
    }

    @Override
    public List<Workshop> findByLevel(Level level) {
        return workshops.findByLevel(level);
    }

    @Override
    public Optional<Workshop> findById(Long id) {
        return workshops.findById(id);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookings.save(booking);
    }
}
