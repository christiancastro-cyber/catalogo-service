package com.dorcas.catalog.workshop.infrastructure.web;

import com.dorcas.catalog.workshop.application.WorkshopService;
import com.dorcas.catalog.workshop.domain.Booking;
import com.dorcas.catalog.workshop.domain.Level;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/workshops")
public class WorkshopController {

    private final WorkshopService workshopService;

    public WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @GetMapping
    public List<WorkshopOutputDto> list(@RequestParam(required = false) Level level,
                                        @RequestParam(required = false) Boolean withFreeSeats) {
        return workshopService.list(level, withFreeSeats).stream()
                .map(WorkshopOutputDto::from)
                .toList();
    }

    @GetMapping("/{id}")
    public WorkshopOutputDto get(@PathVariable Long id) {
        return WorkshopOutputDto.from(workshopService.findById(id));
    }

    @GetMapping("/{id}/bookings")
    public List<BookingOutputDto> listBookings(@PathVariable Long id) {
        return workshopService.listBookings(id).stream()
                .map(booking -> BookingOutputDto.from(id, booking))
                .toList();
    }

    @PostMapping("/{id}/bookings")
    public ResponseEntity<BookingOutputDto> enroll(@PathVariable Long id,
                                                   @Valid @RequestBody BookingInputDto input) {
        Booking booking = workshopService.enroll(id, input.student());
        return ResponseEntity
                .created(URI.create("/api/workshops/" + id + "/bookings/" + booking.getId()))
                .body(BookingOutputDto.from(id, booking));
    }

    @DeleteMapping("/{id}/bookings/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long id, @PathVariable Long bookingId) {
        workshopService.cancel(id, bookingId);
    }

    @GetMapping("/{id}/kit")
    public KitOutputDto kit(@PathVariable Long id) {
        return KitOutputDto.from(workshopService.kitDetails(id));
    }

}
