package com.dorcas.catalog.workshop.domain;

import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    @Column(nullable = false, length = 80)
    private String student;

    protected Booking() {
    }

    public Booking(Workshop workshop, String student) {
        this.workshop = workshop;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public String getStudent() {
        return student;
    }
}
