package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Post;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {

  List<Post> getAll();

  List<Post> getAllOrderedByCreationDate();

  List<Post> getAllByCategory(UUID categoryId);

  Optional<Post> getById(UUID id);

  Post create(String title, String description, String creationDate, UUID categoryId);

  Post put(UUID id, String title, String description, String creationDate, UUID categoryId);

  boolean delete(UUID id);
}
