package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.exeptions.CategoryNotFoundByIDException;
import com.dauphine.blogger.exeptions.PostNotFoundByIDException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.PostService;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
  public List<Post> getAllByCategoryId(UUID categoryId)
      throws CategoryNotFoundByIDException {
    if (!categoryRepository.existsById(categoryId)) {
      throw new CategoryNotFoundByIDException(categoryId);
    }
    return postRepository.findAllByCategoryId(categoryId);
  }

  @Override
  public List<Post> getAll() {
    return postRepository.findAll();
  }

  @Override
  public List<Post> getAllLikeTitleOrContent(String value) {
    return postRepository.findAllLikeTitleOrContent(value);
  }

  @Override
  public List<Post> getAllOrderedByCreationDate() {
    return postRepository.findAll(Sort.by(Direction.DESC, "createdDate"));
  }

  @Override
  public Post getById(UUID id) throws PostNotFoundByIDException {
    return postRepository.findById(id).orElseThrow(
        () -> new PostNotFoundByIDException(id));
  }

  @Override
  public Post put(UUID id, String title, String description, UUID categoryId)
      throws PostNotFoundByIDException, CategoryNotFoundByIDException {
    Category category = categoryRepository.findById(categoryId).orElseThrow(
        () -> new CategoryNotFoundByIDException(categoryId)
    );
    Post post = postRepository.findById(id).orElseThrow(
        () -> new PostNotFoundByIDException(id)
    );
    post.setCategory(category);
    post.setTitle(title);
    post.setContent(description);
    return postRepository.save(post);
  }

  @Override
  public Post create(String title, String content, UUID categoryId)
      throws CategoryNotFoundByIDException {
    Category category = categoryRepository.findById(categoryId).orElseThrow(
        () -> new CategoryNotFoundByIDException(categoryId)
    );
    Post post = new Post(title, content, category);
    return postRepository.save(post);
  }

  @Override
  public boolean delete(UUID id) throws PostNotFoundByIDException {
    if (!postRepository.existsById(id)) {
      throw new PostNotFoundByIDException(id);
    }
    postRepository.deleteById(id);
    return true;
  }
}
