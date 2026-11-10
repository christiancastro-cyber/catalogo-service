package com.dorcas.catalog.workshop.domain;

import com.dorcas.catalog.shared.domain.NotFoundException;

public class BookingNotFoundException extends NotFoundException {

    public BookingNotFoundException(Long workshopId, Long bookingId) {
        super(WorkshopErrorCode.BOOKING_NOT_FOUND, "Booking " + bookingId + " not found in workshop " + workshopId);
    }
}
