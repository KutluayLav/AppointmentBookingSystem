package com.kutluayulutas.booking.service.service;

import com.kutluayulutas.booking.service.dto.SalonDTO;
import com.kutluayulutas.booking.service.dto.ServiceOfferDto;
import com.kutluayulutas.booking.service.dto.UserDTO;
import com.kutluayulutas.booking.service.modal.Booking;

import java.util.Set;

public interface IBookingService {

    Booking createBooking(BookingRequest booking, UserDTO userDTO,
                          SalonDTO salonDTO,
                          Set<ServiceOfferDto> serviceOfferDto);

}
