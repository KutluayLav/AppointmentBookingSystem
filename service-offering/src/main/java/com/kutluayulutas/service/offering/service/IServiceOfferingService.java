package com.kutluayulutas.service.offering.service;

import com.kutluayulutas.service.offering.dto.CategoryDTO;
import com.kutluayulutas.service.offering.dto.SalonDTO;
import com.kutluayulutas.service.offering.dto.ServiceOfferDto;
import com.kutluayulutas.service.offering.model.ServiceOffering;

import java.util.Set;

public interface IServiceOfferingService {

    ServiceOffering createServiceOffering(SalonDTO salonDTO
                                         ,ServiceOfferDto serviceOfferDto
                                         ,CategoryDTO categoryDto);

    ServiceOffering updateServiceOffering(Long serviceOfferingId,
                                          ServiceOffering serviceOffering) throws Exception;

    Set<ServiceOffering> getAllServiceOfferingsBySalonId(Long salonId,Long categoryId);

    Set<ServiceOffering> getServicesByIds(Set<Long> serviceOfferingIds);

    ServiceOffering getServiceOfferingById(Long serviceOfferingId) throws Exception;
}
