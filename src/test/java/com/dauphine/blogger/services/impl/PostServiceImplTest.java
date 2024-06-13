package com.dauphine.blogger.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.dauphine.blogger.exeptions.CategoryNotFoundByIDException;
import com.dauphine.blogger.exeptions.PostNotFoundByIDException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.repositories.PostRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

class PostServiceImplTest {

  private PostRepository postRepository;
  private CategoryRepository categoryRepository;
  private PostServiceImpl postService;

  @BeforeEach
  void setUp() {
    postRepository = mock(PostRepository.class);
    categoryRepository = mock(CategoryRepository.class);
    postService = new PostServiceImpl(postRepository, categoryRepository);
  }

  @Test
  void shouldReturnAllPosts() {
    //Arrange
    Post post1 = new Post("Title1", "Content1", new Category("Category1"));
    Post post2 = new Post("Title2", "Content2", new Category("Category2"));
    when(postRepository.findAll()).thenReturn(List.of(post1, post2));

    //Act
    List<Post> actual = postService.getAll();

    //Assert
    assertEquals(List.of(post1, post2), actual);
  }

  @Test
  void shouldReturnAllPostsOrderedByCreationDate() {
    //Arrange
    Post post1 = new Post("Title1", "Content1", new Category("Category1"));
    Post post2 = new Post("Title2", "Content2", new Category("Category2"));
    when(postRepository.findAll(Sort.by(Direction.DESC, "createdDate"))).thenReturn(
        List.of(post2, post1));

    //Act
    List<Post> actual = postService.getAllOrderedByCreationDate();

    //Assert
    assertEquals(List.of(post2, post1), actual);
  }

  @Test
  void shouldReturnAllPostsLikeTitleOrContent() {
    //Arrange
    Post post1 = new Post("Title1", "Content1", new Category("Category1"));
    Post post2 = new Post("Title2", "Content2", new Category("Category2"));
    when(postRepository.findAllLikeTitleOrContent("Title")).thenReturn(List.of(post1, post2));

    //Act
    List<Post> actual = postService.getAllLikeTitleOrContent("Title");

    //Assert
    assertEquals(List.of(post1, post2), actual);
  }

  @Test
  void shouldReturnAllPostsByCategoryIdWhenCategoryIdExists() throws CategoryNotFoundByIDException {
    //Arrange
    Post post1 = new Post("Title1", "Content1", new Category("Category1"));
    Post post2 = new Post("Title2", "Content2", new Category("Category2"));
    when(postRepository.findAllByCategoryId(post1.getCategory().getId())).thenReturn(
        List.of(post1));
    when(categoryRepository.existsById(post1.getCategory().getId())).thenReturn(
        true);

    //Act
    List<Post> actual = postService.getAllByCategoryId(post1.getCategory().getId());

    //Assert
    assertEquals(List.of(post1), actual);
  }

  @Test
  void shouldThrowExceptionWhenCategoryIdNotExists() {
    //Arrange
    UUID categoryId = UUID.randomUUID();
    when(categoryRepository.existsById(categoryId)).thenReturn(false);

    //Act
    CategoryNotFoundByIDException exception = assertThrows(
        CategoryNotFoundByIDException.class, () -> postService.getAllByCategoryId(categoryId));

    //Assert
    assertEquals("Category with id " + categoryId + " not found", exception.getMessage());
  }

  @Test
  void shouldReturnPostWhenIDExists() throws PostNotFoundByIDException {
    //Arrange
    Post post = new Post("Title", "Content", new Category("Category"));
    when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));

    //Act
    Post actual = postService.getById(post.getId());

    //Assert
    assertEquals(post, actual);
  }

  @Test
  void shouldThrowExceptionWhenIDNotExists() {
    //Arrange
    UUID id = UUID.randomUUID();
    when(postRepository.findById(id)).thenReturn(Optional.empty());

    //Act
    PostNotFoundByIDException exception = assertThrows(
        PostNotFoundByIDException.class, () -> postService.getById(id));

    //Assert
    assertEquals("Post with id " + id + " not found", exception.getMessage());
  }

  @Test
  void shouldPutPostWhenIDExistsAndCategoryIDExists()
      throws PostNotFoundByIDException, CategoryNotFoundByIDException {
    //Arrange
    UUID id = UUID.randomUUID();
    UUID categoryId = UUID.randomUUID();
    Post post = new Post("Title", "Content", new Category("Category"));
    Category category = new Category("Category");
    when(postRepository.findById(id)).thenReturn(Optional.of(post));
    when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
    when(postRepository.save(post)).thenReturn(post);

    //Act
    Post actual = postService.put(id, "Title", "Content", categoryId);

    //Assert
    assertEquals("Title", actual.getTitle());
    assertEquals("Content", actual.getContent());
    assertEquals(category, actual.getCategory());
  }

  @Test
  void shouldThrowExceptionWhenCategoryIDNotExistsWhenPutPost() {
    //Arrange
    UUID id = UUID.randomUUID();
    UUID categoryId = UUID.randomUUID();
    when(postRepository.findById(id)).thenReturn(Optional.of(new Post()));
    when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

    //Act
    CategoryNotFoundByIDException exception = assertThrows(
        CategoryNotFoundByIDException.class,
        () -> postService.put(id, "Title", "Content", categoryId));

    //Assert
    assertEquals("Category with id " + categoryId + " not found", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenIDNotExistsWhenPutPost() {
    //Arrange
    UUID id = UUID.randomUUID();
    UUID categoryId = UUID.randomUUID();
    when(postRepository.findById(id)).thenReturn(Optional.empty());
    when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(new Category()));
    //Act
    PostNotFoundByIDException exception = assertThrows(
        PostNotFoundByIDException.class,
        () -> postService.put(id, "Title", "Content", categoryId));

    //Assert
    assertEquals("Post with id " + id + " not found", exception.getMessage());
  }

  @Test
  void shouldCreatePostWhenCategoryIDExists() throws CategoryNotFoundByIDException {
    //Arrange
    UUID categoryId = UUID.randomUUID();
    Category category = new Category("Category");
    when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
    Post post = new Post("Title", "Content", category);
    when(postRepository.save(any())).thenReturn(post);

    //Act
    Post actual = postService.create("Title", "Content", categoryId);

    //Assert
    assertEquals("Title", actual.getTitle());
    assertEquals("Content", actual.getContent());
    assertEquals(category, actual.getCategory());
  }


  @Test
  void shouldThrowExceptionWhenCategoryIDNotExistsWhenCreatePost() {
    //Arrange
    UUID categoryId = UUID.randomUUID();
    when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

    //Act
    CategoryNotFoundByIDException exception = assertThrows(
        CategoryNotFoundByIDException.class,
        () -> postService.create("Title", "Content", categoryId));

    //Assert
    assertEquals("Category with id " + categoryId + " not found", exception.getMessage());
  }

  @Test
  void shouldDeletePostWhenIDExists() throws PostNotFoundByIDException {
    //Arrange
    Post post = new Post("Title", "Content", new Category("Category"));
    when(postRepository.findById(post.getId())).thenReturn(java.util.Optional.of(post));
    when(postRepository.existsById(post.getId())).thenReturn(true);
    //Act
    boolean actual = postService.delete(post.getId());

    //Assert
    assertEquals(true, actual);
  }

  @Test
  void shouldThrowExceptionWhenIDNotExistsWhenDeletePost() {
    //Arrange
    UUID id = UUID.randomUUID();
    when(postRepository.findById(id)).thenReturn(Optional.empty());

    //Act
    PostNotFoundByIDException exception = assertThrows(
        PostNotFoundByIDException.class,
        () -> postService.delete(id));

    //Assert
    assertEquals("Post with id " + id + " not found", exception.getMessage());
  }

}