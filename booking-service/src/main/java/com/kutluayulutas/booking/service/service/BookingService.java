package com.kutluayulutas.booking.service.service;

import com.kutluayulutas.booking.service.domain.BookingStatus;
import com.kutluayulutas.booking.service.dto.BookingRequest;
import com.kutluayulutas.booking.service.dto.SalonDTO;
import com.kutluayulutas.booking.service.dto.ServiceOfferDto;
import com.kutluayulutas.booking.service.dto.UserDTO;
import com.kutluayulutas.booking.service.modal.Booking;
import com.kutluayulutas.booking.service.modal.SalonReport;
import com.kutluayulutas.booking.service.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {


    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(BookingRequest bookingRequest,
                                 UserDTO userDTO,
                                 SalonDTO salonDTO,
                                 Set<ServiceOfferDto> serviceOfferDto) throws Exception {

        int totalDuration = serviceOfferDto.stream().mapToInt(ServiceOfferDto::getDuration).sum();

        LocalDateTime bookingStartTime = bookingRequest.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);

        Boolean isSlotAvailable = isTimeSlotAvailable(salonDTO,bookingStartTime,bookingEndTime);

        int totalPrice = serviceOfferDto.stream().mapToInt(ServiceOfferDto::getPrice).sum();

        Set<Long> idList = serviceOfferDto.stream()
                .map(ServiceOfferDto::getId)
                .collect(Collectors.toSet());

        Booking newBooking = new Booking();
        newBooking.setCustomerId(userDTO.getId());
        newBooking.setSalonId(salonDTO.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartDateTime(bookingStartTime);
        newBooking.setEndDateTime(bookingEndTime);
        newBooking.setTotalPrice(totalPrice);

        return bookingRepository.save(newBooking);
    }

    private Boolean isTimeSlotAvailable(SalonDTO salonDTO,
                                        LocalDateTime bookingStartTime,
                                        LocalDateTime bookingEndTime) throws Exception {

        List<Booking> existingBookings =getBookingsBySalonId(salonDTO.getId());

        LocalDateTime salonOpenTime =salonDTO.getStartTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime salonCloseTime =salonDTO.getEndTime().atDate(bookingStartTime.toLocalDate());
        // salonun açık olduğu zaman aralığında olmalı
        if(bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)) {
            throw new Exception("Booking Time must be within salon's working hours !");
        }

        for (Booking existBooking : existingBookings) {
            LocalDateTime existingBookingStartTime = existBooking.getStartDateTime();
            LocalDateTime existingBookingEndTime = existBooking.getEndDateTime();

            if(bookingStartTime.isBefore(existingBookingEndTime) &&
                    bookingEndTime.isAfter(existingBookingStartTime)) {
                throw new Exception("Slot Not Available choose different time.");
            }
            if (bookingStartTime.isEqual(existingBookingStartTime) || bookingEndTime.isEqual(existingBookingEndTime)) {
                throw new Exception("Slot Not Available choose different time.");
            }
        }
        return true;
    }

    @Override
    public List<Booking> getBookingsByCustomerId(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsBySalonId(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingById(Long id) throws Exception {

        Booking booking = bookingRepository.findById(id).orElse(null);

        if (booking == null) {
            throw new Exception("Booking Not Found");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus bookingStatus) throws Exception {

        Booking booking = getBookingById(bookingId);

        booking.setStatus(bookingStatus);

        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long salonId) {
        List<Booking> allBookings = getBookingsBySalonId(salonId);

        if (date ==null) {
            return allBookings;
        }
       return allBookings.stream()
                .filter(booking -> isSameDate(booking.getStartDateTime(),date)
                        || isSameDate(booking.getEndDateTime(),date)).collect(Collectors.toList());
    }

    @Override
    public SalonReport getSalonReport(Long salonId) {

        List<Booking> allBookings = getBookingsBySalonId(salonId);

        Double totalEarnings =allBookings.stream().mapToDouble(Booking::getTotalPrice).sum();

        Integer totalBookings = allBookings.size();

        List<Booking> canceledBookings = allBookings.stream()
                .filter(booking -> booking.getStatus().equals(BookingStatus.CANCELLED))
                .collect(Collectors.toList());

        Double totalRefund =canceledBookings.stream().mapToDouble(Booking::getTotalPrice).sum();

        SalonReport salonReport = new SalonReport();
        salonReport.setSalonId(salonId);
        salonReport.setTotalEarnings(totalEarnings);
        salonReport.setTotalBookings(totalBookings);
        salonReport.setTotalRefund(totalRefund);
        salonReport.setCanceledBookings(canceledBookings.size());

        return salonReport;

    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }
}
