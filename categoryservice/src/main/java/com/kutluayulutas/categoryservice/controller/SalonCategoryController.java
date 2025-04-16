package com.kutluayulutas.categoryservice.controller;


import com.kutluayulutas.categoryservice.dto.SalonDTO;
import com.kutluayulutas.categoryservice.modal.Category;
import com.kutluayulutas.categoryservice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/categories/salon-owner")
public class SalonCategoryController {
    private final CategoryService categoryService;

    public SalonCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping()
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        Category savedCategory = categoryService.createCategory(category,salonDTO);

        return ResponseEntity.ok(savedCategory);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) throws Exception {

        SalonDTO salonDTO = new SalonDTO();
        salonDTO.setId(1L);

        categoryService.deleteCategoryById(id,salonDTO.getId());

        return ResponseEntity.ok("Deleted Category Successfully");

    }
}
