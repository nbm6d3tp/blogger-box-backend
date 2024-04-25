package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationCategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import java.util.UUID;
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
  public String getAll() {
    StringBuilder result = new StringBuilder("All categories:<br/>");
    for (Category category : categoryService.getAll()) {
      result.append(category.toString()).append("<br/>");
    }
    return result.toString();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a category by ID endpoint", description = "Retrieve a category by ID")
  public String getByID(@Parameter(description = "ID Category to get") @PathVariable UUID id) {
    String result = "";
    Optional<Category> category = categoryService.getById(id);
    if (category.isPresent()) {
      return category.get().toString();
    }
    return "No category found for ID " + id;
  }

  @PostMapping
  @Operation(summary = "Create a new category endpoint", description = "Create a new category")
  public String create(@RequestBody CreationCategoryRequest body) {
    return "Create a new category";
  }

  @PatchMapping("/{id}/name")
  @Operation(summary = "Change name of a category endpoint", description = "Change name of a category by ID")
  public String patchName(
      @Parameter(description = "ID Category to change name") @PathVariable Integer id,
      @RequestBody String name) {
    return "Update name of category of id " + id;
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a category endpoint", description = "Delete a category by ID")
  public String delete(@Parameter(description = "ID Category to delete") @PathVariable Integer id) {
    return "Delete category of id " + id;
  }
}