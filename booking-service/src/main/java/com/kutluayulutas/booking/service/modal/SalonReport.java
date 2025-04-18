package com.kutluayulutas.booking.service.modal;

import lombok.Data;

@Data
public class SalonReport {
    private Long salonId;

    private String SalonName;

    private Double totalEarnings;

    private Integer totalBookings;

    private Integer canceledBookings;

    private Double totalRefund;
}
