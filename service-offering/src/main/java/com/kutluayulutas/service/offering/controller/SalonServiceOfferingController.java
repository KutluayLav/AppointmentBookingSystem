package com.kutluayulutas.service.offering.controller;

import com.kutluayulutas.service.offering.dto.CategoryDTO;
import com.kutluayulutas.service.offering.dto.SalonDTO;
import com.kutluayulutas.service.offering.dto.ServiceOfferDto;
import com.kutluayulutas.service.offering.model.ServiceOffering;
import com.kutluayulutas.service.offering.service.ServiceOfferingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    public SalonServiceOfferingController(ServiceOfferingService serviceOfferingService) {
        this.serviceOfferingService = serviceOfferingService;
    }


    @PostMapping()
    public ResponseEntity<ServiceOffering> saveServiceOffering(@RequestBody ServiceOfferDto serviceOfferDto) {

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        // invoke category microservices with using open feign
        categoryDTO.setId(serviceOfferDto.getCategoryId());

        ServiceOffering serviceOffering = serviceOfferingService
                .createServiceOffering(salonDTO,serviceOfferDto,categoryDTO);

        return ResponseEntity.ok(serviceOffering);

    }

    @PostMapping("/{serviceId}")
    public ResponseEntity<ServiceOffering> updateServiceOffering(@PathVariable Long serviceId,
                                                                 @RequestBody ServiceOffering serviceOffer) throws Exception {

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);

        ServiceOffering serviceOffering = serviceOfferingService
                .updateServiceOffering(serviceId,serviceOffer);

        return ResponseEntity.ok(serviceOffering);

    }


}
