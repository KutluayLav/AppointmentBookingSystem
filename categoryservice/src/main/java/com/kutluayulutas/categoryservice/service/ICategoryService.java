package com.kutluayulutas.categoryservice.service;

import com.kutluayulutas.categoryservice.dto.SalonDTO;
import com.kutluayulutas.categoryservice.modal.Category;

import java.util.Set;

public interface ICategoryService {

    Category createCategory(Category category, SalonDTO salonDTO);

    Set<Category> getAllCategoriesBySalonId(Long salonId);

    Category getCategoryById(Long id) throws Exception;

    void deleteCategoryById(Long id,Long salonId) throws Exception;

}
