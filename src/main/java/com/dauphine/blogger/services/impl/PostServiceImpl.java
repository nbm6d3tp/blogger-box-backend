package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.PostService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;

  private final CategoryRepository categoryRepository;

  public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository) {
    this.postRepository = postRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<Post> getAllByCategoryId(UUID categoryId) {
    return postRepository.findAllByCategoryId(categoryId);
  }

  @Override
  public List<Post> getAll() {
    return postRepository.findAll();
  }

  @Override
  public List<Post> getAllOrderedByCreationDate() {
    return List.of();
  }

  @Override
  public Post getById(UUID id) {
    return postRepository.findById(id).orElse(null);
  }

  @Override
  public Post put(UUID id, String title, String description, UUID categoryId) {
    Post post = getById(id);
    Optional<Category> category = categoryRepository.findById(categoryId);
    if (category.isPresent()) {
      post.setCategory(category.get());
    } else {
      throw new RuntimeException("Category " + categoryId + "not found");
    }
    post.setTitle(title);
    post.setContent(description);
    return postRepository.save(post);
  }

  @Override
  public Post create(String title, String content, UUID categoryId) {
    Optional<Category> category = categoryRepository.findById(categoryId);
    if (category.isPresent()) {
      Post post = new Post(title, content, category.get());
      return postRepository.save(post);
    } else {
      throw new RuntimeException("Category " + categoryId + "not found");
    }
  }

  @Override
  public boolean delete(UUID id) {
    postRepository.deleteById(id);
    return true;
  }
}
