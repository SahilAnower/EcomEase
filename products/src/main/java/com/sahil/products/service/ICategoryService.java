package com.sahil.products.service;

import com.sahil.products.dto.CategoryDto;

import java.util.List;

public interface ICategoryService {
    void createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    List<CategoryDto> getCategoriesByName(String name);
}
