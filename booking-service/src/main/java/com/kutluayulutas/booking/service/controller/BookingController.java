package com.kutluayulutas.booking.service.controller;


import com.kutluayulutas.booking.service.domain.BookingStatus;
import com.kutluayulutas.booking.service.dto.*;
import com.kutluayulutas.booking.service.mapper.BookingMapper;
import com.kutluayulutas.booking.service.modal.Booking;
import com.kutluayulutas.booking.service.modal.SalonReport;
import com.kutluayulutas.booking.service.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;



    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest
                                                 ) throws Exception {

        // feign client ile yapılacak userDTO ve SalonDTO open feignclient = comminication between microservices
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);
        salonDTO.setStartTime(LocalTime.now());
        salonDTO.setEndTime(LocalTime.now().plusHours(12));

        Set<ServiceOfferDto> serviceOfferDtoSet = new HashSet<>();
    // feignclient
        ServiceOfferDto serviceOfferDto = new ServiceOfferDto();
        serviceOfferDto.setId(1L);
        serviceOfferDto.setPrice(399);
        serviceOfferDto.setDuration(45);
        serviceOfferDto.setName("Hair cut for men");

        serviceOfferDtoSet.add(serviceOfferDto);

        Booking booking = bookingService.createBooking(bookingRequest,
                userDTO,salonDTO,serviceOfferDtoSet);

        return ResponseEntity.ok(booking);

    }
    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>> getBookingsByCustomerId() {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        List<Booking> bookings = bookingService.getBookingsByCustomerId(userDTO.getId());

        return ResponseEntity.ok(getBookingsDTOs(bookings));

    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>> getBookingsBySalon() {


        List<Booking> bookings = bookingService.getBookingsBySalonId(1L);

        return ResponseEntity.ok(getBookingsDTOs(bookings));

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) throws Exception {

        Booking booking = bookingService.getBookingById(id);

        return ResponseEntity.ok(BookingMapper.toBookingDTO(booking));

    }


    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(@PathVariable Long bookingId,
                                                          @RequestParam BookingStatus bookingStatus
                                                          ) throws Exception {

        Booking booking = bookingService.updateBooking(bookingId, bookingStatus);

        return ResponseEntity.ok(BookingMapper.toBookingDTO(booking));

    }


    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookedSlot(@PathVariable Long salonId,
                                                    @RequestParam(required = false) LocalDate date
                                                    ) throws Exception {

        List<Booking> bookings = bookingService.getBookingsByDate(date ,salonId);

        List<BookingSlotDTO> slotDTOS =bookings.stream()
                .map(booking -> {
                    BookingSlotDTO bookingSlotDTO = new BookingSlotDTO();
                    bookingSlotDTO.setStartTime(booking.getStartDateTime());
                    bookingSlotDTO.setEndTime(booking.getEndDateTime());
                    return bookingSlotDTO;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(slotDTOS);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport> getSalonReport() throws Exception {

        SalonReport salonReport = bookingService.getSalonReport(1L);

        return ResponseEntity.ok(salonReport);

    }



    private Set<BookingDTO> getBookingsDTOs(List<Booking> bookings) {
        return bookings.stream()
                .map(booking -> {
                    return BookingMapper.toBookingDTO(booking);
                }).collect(Collectors.toSet());
    }

}
