package com.kutluayulutas.categoryservice.service;

import com.kutluayulutas.categoryservice.dto.SalonDTO;
import com.kutluayulutas.categoryservice.modal.Category;
import com.kutluayulutas.categoryservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category createCategory(Category category, SalonDTO salonDTO) {
        Category categorySaved = new Category();
        categorySaved.setName(category.getName());
        categorySaved.setImage(category.getImage());
        categorySaved.setSalonId(salonDTO.getId());

        return categoryRepository.save(categorySaved);
    }

    @Override
    public Set<Category> getAllCategoriesBySalonId(Long salonId) {
        return categoryRepository.findBySalonId(salonId);
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            throw new Exception("Category Not Exist with id: "+id);
        }
        return category;
    }

    @Override
    public void deleteCategoryById(Long id,Long salonId) throws Exception {
        Category category = getCategoryById(id);
        if (!category.getSalonId().equals(salonId)){
            throw new Exception("You dont have permission to delete this category");
        }
        categoryRepository.deleteById(id);
    }
}
