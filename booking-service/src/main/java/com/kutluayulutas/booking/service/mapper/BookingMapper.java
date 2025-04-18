package com.kutluayulutas.booking.service.mapper;

import com.kutluayulutas.booking.service.dto.BookingDTO;
import com.kutluayulutas.booking.service.modal.Booking;

public class BookingMapper {

    public static BookingDTO toBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setSalonId(booking.getSalonId());
        bookingDTO.setServiceIds(booking.getServiceIds());
        bookingDTO.setStartDateTime(booking.getStartDateTime());
        bookingDTO.setEndDateTime(booking.getEndDateTime());
        bookingDTO.setStatus(booking.getStatus());

        return bookingDTO;
    }
}
