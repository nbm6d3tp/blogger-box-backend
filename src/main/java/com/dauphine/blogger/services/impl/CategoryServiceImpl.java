package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final List<Category> tempCategories;

  public CategoryServiceImpl() {
    tempCategories = new ArrayList<>();
    tempCategories.add(new Category(UUID.randomUUID(), "Category 1"));
    tempCategories.add(new Category(UUID.randomUUID(), "Category 2"));
    tempCategories.add(new Category(UUID.randomUUID(), "Category 3"));
  }

  @Override
  public List<Category> getAll() {
    return tempCategories;
  }

  @Override
  public Optional<Category> getById(UUID id) {
    for (Category category : tempCategories) {
      if (category.getId() == id) {
        return Optional.of(category);
      }
    }
    return Optional.empty();
  }

  @Override
  public Category create(String name) {
    return null;
  }

  @Override
  public Category updateName(UUID id, String name) {
    return null;
  }

  @Override
  public boolean delete(UUID id) {
    return false;
  }
}
