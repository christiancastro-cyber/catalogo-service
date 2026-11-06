package com.dorcas.catalog.workshop.infrastructure.persistence;

import com.dorcas.catalog.workshop.domain.Level;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workshop")
class WorkshopEntity {

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
    private List<BookingEntity> bookings = new ArrayList<>();

    protected WorkshopEntity() {
    }

    WorkshopEntity(String name, Level level, int seats, double price) {
        this.name = name;
        this.level = level;
        this.seats = seats;
        this.price = price;
    }

    Long getId() { return id; }
    String getName() { return name; }
    Level getLevel() { return level; }
    int getSeats() { return seats; }
    double getPrice() { return price; }
    List<BookingEntity> getBookings() { return bookings; }
}