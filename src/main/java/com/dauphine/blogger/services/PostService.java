package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Post;
import java.util.List;
import java.util.UUID;

public interface PostService {

  List<Post> getAll();

  List<Post> getAllLikeTitleOrContent(String value);

  List<Post> getAllOrderedByCreationDate();

  List<Post> getAllByCategoryId(UUID categoryId);

  Post getById(UUID id);

  Post put(UUID id, String title, String description, UUID categoryId);

  boolean delete(UUID id);

  Post create(String title, String content, UUID categoryId);

}
