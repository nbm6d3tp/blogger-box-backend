package com.dauphine.blogger.exeptions;

import java.util.UUID;

public class PostNotFoundByIDException extends Exception {

  public PostNotFoundByIDException(UUID id) {
    super("Post with id " + id + " not found");
  }
}
