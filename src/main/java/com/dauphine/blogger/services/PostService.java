package com.dauphine.blogger.services;

import com.dauphine.blogger.exeptions.CategoryNotFoundByIDException;
import com.dauphine.blogger.exeptions.PostNotFoundByIDException;
import com.dauphine.blogger.models.Post;
import java.util.List;
import java.util.UUID;

public interface PostService {

  List<Post> getAll();

  List<Post> getAllLikeTitleOrContent(String value);

  List<Post> getAllOrderedByCreationDate();

  List<Post> getAllByCategoryId(UUID categoryId) throws CategoryNotFoundByIDException;

  Post getById(UUID id) throws PostNotFoundByIDException;

  Post put(UUID id, String title, String description, UUID categoryId)
      throws PostNotFoundByIDException, CategoryNotFoundByIDException;

  boolean delete(UUID id) throws PostNotFoundByIDException;

  Post create(String title, String content, UUID categoryId) throws CategoryNotFoundByIDException;

}
