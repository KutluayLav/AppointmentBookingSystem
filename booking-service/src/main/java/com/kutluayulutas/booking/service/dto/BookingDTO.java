package com.kutluayulutas.booking.service.dto;

import com.kutluayulutas.booking.service.domain.BookingStatus;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDTO {

    private Long id;

    private Long salonId;

    private Long customerId;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Set<Long> serviceIds;

    private BookingStatus status=BookingStatus.PENDING;

    private int totalPrice;
}
