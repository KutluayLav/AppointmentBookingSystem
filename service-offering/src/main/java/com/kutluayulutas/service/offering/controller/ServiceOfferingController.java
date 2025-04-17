package com.kutluayulutas.service.offering.controller;


import com.kutluayulutas.service.offering.model.ServiceOffering;
import com.kutluayulutas.service.offering.service.ServiceOfferingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/service-offering")
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    public ServiceOfferingController(ServiceOfferingService serviceOfferingService) {
        this.serviceOfferingService = serviceOfferingService;
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesBySalonId(@PathVariable Long salonId,
                                                                     @RequestParam(required = false) Long categoryId) {
        Set<ServiceOffering> serviceOfferingSet = serviceOfferingService
                .getAllServiceOfferingsBySalonId(salonId,categoryId);
        return ResponseEntity.ok(serviceOfferingSet);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServiceById(@PathVariable Long id) throws Exception {
        ServiceOffering serviceOfferingSet = serviceOfferingService
                .getServiceOfferingById(id);
        return ResponseEntity.ok(serviceOfferingSet);
    }

    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(@PathVariable Set<Long> ids) {

        Set<ServiceOffering> serviceOfferingSet = serviceOfferingService
                .getServicesByIds(ids);

        return ResponseEntity.ok(serviceOfferingSet);
    }
}
