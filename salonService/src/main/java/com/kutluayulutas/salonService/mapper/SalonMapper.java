package com.kutluayulutas.salonService.mapper;

import com.kutluayulutas.salonService.modal.Salon;
import com.kutluayulutas.salonService.payload.dto.SalonDTO;

public class SalonMapper {

    public static SalonDTO mapToDTO(Salon salon) {


        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(salon.getId());

        salonDTO.setName(salon.getName());
        salonDTO.setImages(salon.getImages());
        salonDTO.setEmail(salon.getEmail());
        salonDTO.setPhoneNumber(salon.getPhoneNumber());
        salonDTO.setAddress(salon.getAddress());
        salonDTO.setCity(salon.getCity());
        salonDTO.setStartTime(salon.getStartTime());
        salonDTO.setEndTime(salon.getEndTime());
        salonDTO.setOwnerId(salon.getOwnerId());

        return salonDTO;
    }
}
