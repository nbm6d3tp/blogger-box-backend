package com.dauphine.blogger.services;

import com.dauphine.blogger.exeptions.CategoryAlreadyExistedException;
import com.dauphine.blogger.exeptions.CategoryNotFoundByIDException;
import com.dauphine.blogger.models.Category;
import java.util.List;
import java.util.UUID;

public interface CategoryService {

  List<Category> getAll();

  List<Category> getAllLikeName(String name);

  Category getById(UUID id) throws CategoryNotFoundByIDException;

  Category create(String name) throws CategoryAlreadyExistedException;

  Category updateName(UUID id, String name)
      throws CategoryNotFoundByIDException, CategoryAlreadyExistedException;

  boolean delete(UUID id) throws CategoryNotFoundByIDException;

}
