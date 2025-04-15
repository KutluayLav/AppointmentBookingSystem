package com.kutluayulutas.salonService.service;

import com.kutluayulutas.salonService.modal.Salon;
import com.kutluayulutas.salonService.payload.dto.SalonDTO;
import com.kutluayulutas.salonService.payload.dto.UserDTO;

import java.util.List;

public interface ISalonService {

    Salon createSalon(SalonDTO salon, UserDTO user);

    Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId);

    List<Salon> findAllSalons();

    Salon findSalonById(Long salonId);

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> searchSalonByCity(String city);

}
