package com.sahil.products.controller;

import com.sahil.products.constants.CategoryConstants;
import com.sahil.products.dto.CategoryDto;
import com.sahil.products.dto.ResponseDto;
import com.sahil.products.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService iCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        iCategoryService.createCategory(categoryDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CategoryConstants.STATUS_201, CategoryConstants.MESSAGE_201));
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<List<CategoryDto>> getAllCategories () {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iCategoryService.getAllCategories());
    }

    @GetMapping("/fetch-by-name")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByName (@RequestParam String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iCategoryService.getCategoriesByName(name));
    }
}
