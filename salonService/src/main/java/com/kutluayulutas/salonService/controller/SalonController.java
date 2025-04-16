package com.kutluayulutas.salonService.controller;

import com.kutluayulutas.salonService.mapper.SalonMapper;
import com.kutluayulutas.salonService.modal.Salon;
import com.kutluayulutas.salonService.payload.dto.SalonDTO;
import com.kutluayulutas.salonService.payload.dto.UserDTO;
import com.kutluayulutas.salonService.service.SalonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
public class SalonController {

    private final SalonService salonService;

    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }

    @PostMapping("/save")
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon salon = salonService.createSalon(salonDTO,userDTO);

        SalonDTO salonDTOResponse = SalonMapper.mapToDTO(salon);

        return ResponseEntity.ok(salonDTOResponse);
    }

    @PatchMapping("/{salonId}")
    public ResponseEntity<SalonDTO> updateSalon(@RequestBody SalonDTO salonDTO,
                                                @PathVariable Long salonId) throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon salon = salonService.updateSalon(salonDTO,userDTO,salonId);

        SalonDTO salonDTOResponse = SalonMapper.mapToDTO(salon);

        return ResponseEntity.ok(salonDTOResponse);
    }

    @GetMapping("/getSalons")
    public ResponseEntity<List<SalonDTO>> findAllSalons(@RequestBody SalonDTO salonDTO) throws Exception {

        List<Salon> salons =salonService.findAllSalons();

        List<SalonDTO> salonDTOList = salons.stream().map((salon) ->
                {
                    SalonDTO salonDTOResponse = SalonMapper.mapToDTO(salon);
                    return salonDTOResponse;
                }
        ).toList();


        return ResponseEntity.ok(salonDTOList);
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable Long salonId) throws Exception {

        Salon salon = salonService.findSalonById(salonId);

        SalonDTO salonDTOResponse = SalonMapper.mapToDTO(salon);

        return ResponseEntity.ok(salonDTOResponse);

    }

    //http://localhost:5002/api/salons/search?city=
    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(
            @RequestParam("city") String city)
            throws Exception {

        List<Salon> salons =salonService.searchSalonByCity(city);

        List<SalonDTO> salonDTOList = salons.stream().map((salon) ->
                {
                    SalonDTO salonDTOResponse = SalonMapper.mapToDTO(salon);
                    return salonDTOResponse;
                }
        ).toList();


        return ResponseEntity.ok(salonDTOList);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<SalonDTO> getSalonByOwnerId() throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon salon = salonService.getSalonByOwnerId(userDTO.getId());

        SalonDTO salonDTOResponse = SalonMapper.mapToDTO(salon);

        return ResponseEntity.ok(salonDTOResponse);
    }

}
