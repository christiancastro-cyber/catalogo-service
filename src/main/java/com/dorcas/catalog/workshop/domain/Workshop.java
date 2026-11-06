package com.dorcas.catalog.workshop.domain;


import java.util.ArrayList;
import java.util.List;

public class Workshop {

    private Long id;

    private String name;

    private Level level;

    private int seats;
    private double price;

    private List<Booking> bookings;

    protected Workshop() {
    }

    public Workshop(String name, Level level, int seats, double price) {
        this(null, name, level, seats, price, List.of());
    }

    public Workshop(Long id, String name, Level level, int seats, double price, List<Booking> bookings) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.seats = seats;
        this.price = price;
        this.bookings = new ArrayList<>(bookings);
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
        Booking booking = new Booking(student);
        bookings.add(booking);
        return booking;
    }

    public void cancel(Long bookingId) {
        boolean removed = bookings.removeIf(b -> b.getId().equals(bookingId));
        if (!removed) {
            throw new BookingNotFoundException(id, bookingId);
        }
    }

    public Booking findBookingOf(String student) {
        return bookings.stream()
                .filter(b -> b.getStudent().equalsIgnoreCase(student))
                .findFirst()
                .orElse(null);
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
