package com.kutluayulutas.booking.service.service;

import com.kutluayulutas.booking.service.domain.BookingStatus;
import com.kutluayulutas.booking.service.dto.BookingRequest;
import com.kutluayulutas.booking.service.dto.SalonDTO;
import com.kutluayulutas.booking.service.dto.ServiceOfferDto;
import com.kutluayulutas.booking.service.dto.UserDTO;
import com.kutluayulutas.booking.service.modal.Booking;
import com.kutluayulutas.booking.service.modal.SalonReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IBookingService {

    Booking createBooking(BookingRequest booking, UserDTO userDTO,
                          SalonDTO salonDTO,
                          Set<ServiceOfferDto> serviceOfferDto) throws Exception;

    List<Booking> getBookingsByCustomerId(Long customerId);

    List<Booking> getBookingsBySalonId(Long salonId);

    Booking getBookingById(Long id) throws Exception;

    Booking updateBooking(Long bookingId, BookingStatus bookingStatus) throws Exception;

    List<Booking> getBookingsByDate(LocalDate date,Long salonId);

    SalonReport getSalonReport(Long salonId);

}
