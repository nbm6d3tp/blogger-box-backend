package com.dauphine.blogger.exeptions;

public class CategoryAlreadyExistedException extends Exception {

  public CategoryAlreadyExistedException(String name) {
    super("Category with name " + name + " already existed.");
  }
}
