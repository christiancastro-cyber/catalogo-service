package com.dorcas.catalog.workshop.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workshop_id")
    private WorkshopEntity workshop;

    @Column(nullable = false, length = 80)
    private String student;

    protected BookingEntity() {
    }

    BookingEntity(WorkshopEntity workshop, String student) {
        this.workshop = workshop;
        this.student = student;
    }

    Long getId() { return id; }
    String getStudent() { return student; }
}