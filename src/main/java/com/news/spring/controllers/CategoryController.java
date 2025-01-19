package com.news.spring.controllers;

import com.news.spring.dto.CategoryDto;
import com.news.spring.services.CategoryCRUDService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryCRUDService categoryCRUDService;

    public CategoryController(CategoryCRUDService categoryCRUDService) {
        this.categoryCRUDService = categoryCRUDService;
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryCRUDService.getById(id);
    }

    @GetMapping
    public List<CategoryDto> getAllNews() {
        return categoryCRUDService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto) {
        categoryCRUDService.create(categoryDto);
        return categoryDto;
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        categoryCRUDService.update(id, categoryDto);
        return categoryDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable Long id) {
        categoryCRUDService.deleteById(id);
    }
}
