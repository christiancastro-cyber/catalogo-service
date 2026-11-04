package com.dorcas.catalog.workshop.infrastructure.persistence;

import com.dorcas.catalog.workshop.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

interface BookingJpaRepository extends JpaRepository<Booking, Long> {
}
