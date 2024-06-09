package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exeptions.CategoryAlreadyExistedException;
import com.dauphine.blogger.exeptions.CategoryNotFoundByIDException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository repository;

  public CategoryServiceImpl(CategoryRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Category> getAll() {
    return repository.findAll();
  }

  @Override
  public List<Category> getAllLikeName(String name) {
    return repository.findAllLikeName(name);
  }

  @Override
  public Category getById(UUID id) throws CategoryNotFoundByIDException {
    return repository.findById(id).orElseThrow(() -> new CategoryNotFoundByIDException(id));
  }

  @Override
  public Category create(String name) throws CategoryAlreadyExistedException {
    if (repository.existsByNameLike(name)) {
      throw new CategoryAlreadyExistedException(name);
    }
    Category category = new Category(name);
    return repository.save(category);
  }

  @Override
  public Category updateName(UUID id, String name)
      throws CategoryNotFoundByIDException, CategoryAlreadyExistedException {
    if (repository.existsByNameLike(name)) {
      throw new CategoryAlreadyExistedException(name);
    }
    Category category = repository.findById(id)
        .orElseThrow(() -> new CategoryNotFoundByIDException(id));
    category.setName(name);
    return repository.save(category);
  }

  @Override
  public boolean delete(UUID id) throws CategoryNotFoundByIDException {
    if (!repository.existsById(id)) {
      throw new CategoryNotFoundByIDException(id);
    }
    repository.deleteById(id);
    return true;
  }
}
