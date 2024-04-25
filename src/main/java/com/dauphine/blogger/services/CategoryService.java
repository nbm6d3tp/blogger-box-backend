package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

  List<Category> getAll();

  Optional<Category> getById(UUID id);

  Category create(String name);

  Category updateName(UUID id, String name);

  boolean delete(UUID id);
}
