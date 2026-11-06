package com.dorcas.catalog.workshop.application;

import com.dorcas.catalog.workshop.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkshopService {

    private final WorkshopRepository repository;

    public WorkshopService(WorkshopRepository repository) {
        this.repository = repository;
    }

    public List<Workshop> list(Level level, Boolean withFreeSeats) {
        List<Workshop> workshops = (level == null)
                ? repository.findAll()
                : repository.findByLevel(level);

        return workshops.stream()
                .filter(w -> withFreeSeats == null || !w.isFull() == withFreeSeats)
                .toList();
    }

    public Workshop findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new WorkshopNotFoundException(id));
    }

    public List<Booking> listBookings(Long workshopId) {
        return findById(workshopId).getBookings();
    }

    @Transactional
    public Booking enroll(Long workshopId, String student) {
        Workshop workshop = findById(workshopId);
        workshop.enroll(student);
        Workshop saved = repository.save(workshop);
        return saved.findBookingOf(student);
    }

    @Transactional
    public void cancel(Long workshopId, Long bookingId) {
        Workshop workshop = findById(workshopId);
        workshop.cancel(bookingId);
        repository.save(workshop);
    }
}
