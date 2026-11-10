package com.dorcas.catalog.workshop.domain;


import java.util.ArrayList;
import java.util.List;

public class Workshop {

    private final Long id;

    private final String name;

    private final Level level;

    private final int seats;
    private final double price;

    private final List<Booking> bookings;

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
        boolean removed = bookings.removeIf(b -> bookingId.equals(b.getId()));
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
