package com.example.wildmovies.controller;

import com.example.wildmovies.entity.Category;
import com.example.wildmovies.repository.CategoryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepositoryInjected) {
        this.categoryRepository = categoryRepositoryInjected;
    }

    @PostMapping("")
    public Category create(@RequestBody Category newCategory) {
        return this.categoryRepository.save(newCategory);
    }
}
