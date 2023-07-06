package com.example.wildmovies.controller;

import com.example.wildmovies.entity.Category;
import com.example.wildmovies.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepositoryInjected) {
        this.categoryRepository = categoryRepositoryInjected;
    }

    @GetMapping("")
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable UUID id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public Category create(@RequestBody Category newCategory) {
        return this.categoryRepository.save(newCategory);
    }
}
