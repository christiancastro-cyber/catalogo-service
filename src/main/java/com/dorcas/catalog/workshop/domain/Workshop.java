package com.dorcas.catalog.workshop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    private int seats;
    private double price;

    @OneToMany(mappedBy = "workshop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    protected Workshop() {
    }

    public Workshop(String name, Level level, int seats, double price) {
        this.name = name;
        this.level = level;
        this.seats = seats;
        this.price = price;
    }

    public Booking enroll(String student) {
        if (isFull()) {
            throw new WorkshopFullException(name, seats);
        }
        boolean alreadyEnrolled = bookings.stream()
                .anyMatch(b -> b.getStudent().equalsIgnoreCase(student));
        if (alreadyEnrolled) {
            throw new StudentAlreadyEnrolledException(student, name);
        }
        Booking booking = new Booking(this, student);
        bookings.add(booking);
        return booking;
    }

    public void cancel(Long bookingId) {
        boolean removed = bookings.removeIf(b -> b.getId().equals(bookingId));
        if (!removed) {
            throw new BookingNotFoundException(id, bookingId);
        }
    }

    public boolean isFull() {
        return bookings.size() >= seats;
    }

    public int freeSeats() {
        return seats - bookings.size();
    }

    public List<Booking> getBookings() {
        return List.copyOf(bookings);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public int getSeats() {
        return seats;
    }

    public double getPrice() {
        return price;
    }
}
