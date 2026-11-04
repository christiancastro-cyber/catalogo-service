package com.dorcas.catalog.workshop.infrastructure.web;

import com.dorcas.catalog.workshop.domain.Booking;

public record BookingOutputDto(Long id, Long workshopId, String student) {

    public static BookingOutputDto from(Long workshopId, Booking booking) {
        return new BookingOutputDto(booking.getId(), workshopId, booking.getStudent());
    }
}
