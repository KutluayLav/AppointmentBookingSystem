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

    @GetMapping
    public ResponseEntity<List<SalonDTO>> findAllSalons(@RequestBody SalonDTO salonDTO) throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        List<Salon> salon =salonService.findAllSalons();

        List<SalonDTO> salonDTOList = salon.stream().map((salon1) ->
                {
                    SalonDTO salonDTOResponse = SalonMapper.mapToDTO(salon1);
                    return salonDTOResponse;
                }
        ).toList();


        return ResponseEntity.ok(salonDTOList);
    }
}
