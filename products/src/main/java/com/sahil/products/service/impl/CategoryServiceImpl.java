package com.sahil.products.service.impl;

import com.sahil.products.dto.CategoryDto;
import com.sahil.products.mapper.GenericMapper;
import com.sahil.products.repository.CategoryRepository;
import com.sahil.products.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final GenericMapper genericMapper;
    private final CategoryRepository categoryRepository;
    @Override
    public void createCategory(CategoryDto categoryDto) {
        categoryRepository.save(genericMapper.categoryDtoToCategory(categoryDto));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(genericMapper::categoryToCategoryDto).toList();
    }

    @Override
    public List<CategoryDto> getCategoriesByName(String name) {
        return categoryRepository.findByNameContainingIgnoreCase(name).stream().map(genericMapper::categoryToCategoryDto).toList();
    }
}
