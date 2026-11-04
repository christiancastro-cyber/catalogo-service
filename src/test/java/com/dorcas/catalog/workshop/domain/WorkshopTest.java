package com.dorcas.catalog.workshop.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class WorkshopTest {

    private Workshop layette() {
        return new Workshop("Taller de Canastilla", Level.BEGINNER, 8, 45.0);
    }

    private Workshop smallLayette() {
        return new Workshop("Taller de Canastilla", Level.BEGINNER, 2, 45.0);
    }

    @Test
    void enrollingAStudentReducesFreeSeatsByOne() {
        Workshop workshop = layette();

        workshop.enroll("María");

        assertThat(workshop.freeSeats()).isEqualTo(7);
    }

    @Test
    void enrollingTheSameStudentTwiceThrowsException() {
        Workshop workshop = smallLayette();

        workshop.enroll("Ana");

        assertThatThrownBy(() -> workshop.enroll("Ana"))
                .isInstanceOf(StudentAlreadyEnrolledException.class);
    }

    @Test
    void enrollingMoreStudentsThanSeatsThrowsWorkshopFull() {
        Workshop workshop = smallLayette();

        workshop.enroll("Ana");
        workshop.enroll("María");

        assertThatThrownBy(() -> workshop.enroll("Curiosa"))
                .isInstanceOf(WorkshopFullException.class);
    }

    @Test
    void cancellingABookingThatDoesNotExistThrowsBookingNotFound() {
        Workshop workshop = layette();

        assertThatThrownBy(() -> workshop.cancel(5L))
                .isInstanceOf(BookingNotFoundException.class);
    }
}
