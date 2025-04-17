package com.kutluayulutas.service.offering.service;

import com.kutluayulutas.service.offering.dto.CategoryDTO;
import com.kutluayulutas.service.offering.dto.SalonDTO;
import com.kutluayulutas.service.offering.dto.ServiceOfferDto;
import com.kutluayulutas.service.offering.model.ServiceOffering;
import com.kutluayulutas.service.offering.repository.ServiceOfferingRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceOfferingService implements IServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    public ServiceOfferingService(ServiceOfferingRepository serviceOfferingRepository) {
        this.serviceOfferingRepository = serviceOfferingRepository;
    }


    @Override
    public ServiceOffering createServiceOffering(SalonDTO salonDTO,
                                                 ServiceOfferDto serviceOfferDto,
                                                 CategoryDTO categoryDto) {

        ServiceOffering serviceOffering = new ServiceOffering();
        serviceOffering.setName(serviceOfferDto.getName());
        serviceOffering.setDescription(serviceOfferDto.getDescription());
        serviceOffering.setPrice(serviceOfferDto.getPrice());
        serviceOffering.setSalonId(salonDTO.getId());
        serviceOffering.setCategoryId(categoryDto.getId());
        serviceOffering.setDuration(serviceOfferDto.getDuration());
        serviceOffering.setImage(serviceOfferDto.getImage());

        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateServiceOffering(Long serviceOfferingId, ServiceOffering serviceOffering) throws Exception {

        ServiceOffering existedserviceOffering =serviceOfferingRepository.findById(serviceOfferingId).orElse(null);

        if (existedserviceOffering == null) {
            throw new Exception("Service Not Exist With id :"+serviceOfferingId);
        }

        existedserviceOffering.setName(serviceOffering.getName());
        existedserviceOffering.setDescription(serviceOffering.getDescription());
        existedserviceOffering.setPrice(serviceOffering.getPrice());
        existedserviceOffering.setDuration(serviceOffering.getDuration());
        existedserviceOffering.setImage(serviceOffering.getImage());

        return serviceOfferingRepository.save(existedserviceOffering);
    }

    @Override
    public Set<ServiceOffering> getAllServiceOfferingsBySalonId(Long salonId, Long categoryId) {

        Set<ServiceOffering> serviceOfferingSet = serviceOfferingRepository.findBySalonId(salonId);

        if (categoryId == null) {
            serviceOfferingSet
                    .stream()
                    .filter((service)->service.getCategoryId() != null && service.getCategoryId()==categoryId)
                    .collect(Collectors.toSet());
        }
        return serviceOfferingSet;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> serviceOfferingIds) {
        List<ServiceOffering> serviceOfferingList = serviceOfferingRepository.findAllById(serviceOfferingIds);
        return new HashSet<>(serviceOfferingList);
    }

    @Override
    public ServiceOffering getServiceOfferingById(Long serviceOfferingId) throws Exception {

        ServiceOffering serviceOffering = serviceOfferingRepository.findById(serviceOfferingId).orElse(null);

        if (serviceOffering == null) {
            throw new Exception("Service Not Found with id:"+serviceOfferingId);
        }

        return serviceOffering;
    }
}
