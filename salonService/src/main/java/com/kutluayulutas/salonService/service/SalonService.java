package com.kutluayulutas.salonService.service;

import com.kutluayulutas.salonService.modal.Salon;
import com.kutluayulutas.salonService.payload.dto.SalonDTO;
import com.kutluayulutas.salonService.payload.dto.UserDTO;
import com.kutluayulutas.salonService.repository.SalonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalonService implements ISalonService {

    private final SalonRepository salonRepository;

    public SalonService(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }


    @Override
    public Salon createSalon(SalonDTO salon, UserDTO user) {

        Salon salonEntity = new Salon();
        salonEntity.setName(salon.getName());
        salonEntity.setEmail(salon.getEmail());
        salonEntity.setPhoneNumber(salon.getPhoneNumber());
        salonEntity.setAddress(salon.getAddress());
        salonEntity.setCity(salon.getCity());
        salonEntity.setOwnerId(user.getId());
        salon.setImages(salon.getImages());
        salonEntity.setStartTime(salon.getStartTime());
        salonEntity.setEndTime(salon.getEndTime());

        return salonRepository.save(salonEntity);
    }

    @Override
    public Salon updateSalon(SalonDTO salon, UserDTO user, Long salonId) throws Exception {

        Salon existingSalon = salonRepository.findById(salonId).orElse(null);

        if (existingSalon != null && salon.getOwnerId().equals(user.getId())) {

            existingSalon.setName(salon.getName());
            existingSalon.setEmail(salon.getEmail());
            existingSalon.setPhoneNumber(salon.getPhoneNumber());
            existingSalon.setAddress(salon.getAddress());
            existingSalon.setCity(salon.getCity());
            existingSalon.setOwnerId(user.getId());
            existingSalon.setImages(salon.getImages());
            existingSalon.setStartTime(salon.getStartTime());
            existingSalon.setEndTime(salon.getEndTime());

            return salonRepository.save(existingSalon);
        }

         throw new Exception("Salon Not Exist");
    }

    @Override
    public List<Salon> findAllSalons() {
        return salonRepository.findAll();
    }

    @Override
    public Salon findSalonById(Long salonId) throws Exception {
        Salon salon = salonRepository.findById(salonId).orElse(null);
        if (salon == null) {
            throw new Exception("Salon Not Exist");
        }
        return salon;
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
}
