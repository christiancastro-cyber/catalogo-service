package com.dorcas.catalog.workshop.domain;

public class Booking {

    private final Long id;
    private final String student;

    public Booking(String student) {
        this(null, student);
    }

    public Booking(Long id, String student) {
        this.id = id;
        this.student = student;
    }

    public Long getId() { return id; }
    public String getStudent() { return student; }
}
