package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  private final List<Post> tempPosts;

  public PostServiceImpl() {
    tempPosts = new ArrayList<>();
    tempPosts.add(
        new Post(UUID.randomUUID(), "Post 1", "Content 1", "2021-01-01",
            new Category(UUID.randomUUID(), "Category 1")));
    tempPosts.add(new Post(UUID.randomUUID(), "Post 2", "Content 2", "2021-01-02",
        new Category(UUID.randomUUID(), "Category 2")));
    tempPosts.add(new Post(UUID.randomUUID(), "Post 3", "Content 3", "2021-01-03",
        new Category(UUID.randomUUID(), "Category 3")));
  }

  @Override
  public List<Post> getAll() {
    return tempPosts;
  }

  @Override
  public List<Post> getAllOrderedByCreationDate() {
    return tempPosts;
  }

  @Override
  public List<Post> getAllByCategory(UUID categoryId) {
    return tempPosts.stream().filter(post -> post.getCategory().getId() == categoryId).toList();
  }

  @Override
  public Optional<Post> getById(UUID id) {
    for (Post post : tempPosts) {
      if (post.getId() == id) {
        return Optional.of(post);
      }
    }
    return Optional.empty();
  }

  @Override
  public Post create(String title, String description, String creationDate, UUID categoryId) {
    return null;
  }

  @Override
  public Post put(UUID id, String title, String description, String creationDate, UUID categoryId) {
    return null;
  }

  @Override
  public boolean delete(UUID id) {
    return false;
  }
}
