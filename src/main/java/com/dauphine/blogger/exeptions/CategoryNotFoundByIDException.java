package com.dauphine.blogger.exeptions;

import java.util.UUID;

public class CategoryNotFoundByIDException extends Exception {

  public CategoryNotFoundByIDException(UUID id) {
    super("Category with id " + id + " not found");
  }
}
