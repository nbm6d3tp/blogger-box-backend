package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.exeptions.CategoryNotFoundByIDException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Category API", description = "Category endpoints")
@RequestMapping("/v1/categories")
public class CategoryController {

  CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  @Operation(summary = "Get all categories endpoint", description = "Retrieve all categories")
  public ResponseEntity<List<Category>> getAll() {
    return ResponseEntity.ok(categoryService.getAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a category by ID endpoint", description = "Retrieve a category by ID")
  public ResponseEntity<Category> getByID(
      @Parameter(description = "ID Category to get") @PathVariable UUID id)
      throws CategoryNotFoundByIDException {
    return ResponseEntity.ok(categoryService.getById(id));
  }

  @PostMapping
  @Operation(summary = "Create a new category endpoint", description = "Create a new category")
  public ResponseEntity<Category> create(@RequestBody CreationCategoryRequest body) {
    Category category = categoryService.create(body.getName());
    return ResponseEntity.created(URI.create("v1/categories/" + category.getId())).
        body(category);
  }

  @PatchMapping("/{id}/name")
  @Operation(summary = "Change name of a category endpoint", description = "Change name of a category by ID")
  public Category patchName(
      @Parameter(description = "ID Category to change name") @PathVariable UUID id,
      @RequestBody String name) {
    return categoryService.updateName(id, name);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a category endpoint", description = "Delete a category by ID")
  public boolean delete(
      @Parameter(description = "ID Category to delete") @PathVariable UUID id) {
    return categoryService.delete(id);
  }
}