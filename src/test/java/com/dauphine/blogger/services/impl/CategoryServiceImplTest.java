package com.dauphine.blogger.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.dauphine.blogger.exeptions.CategoryAlreadyExistedException;
import com.dauphine.blogger.exeptions.CategoryNotFoundByIDException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryServiceImplTest {

  private CategoryRepository categoryRepository;
  private CategoryServiceImpl categoryService;

  @BeforeEach
  void setUp() {
    categoryRepository = mock(CategoryRepository.class);
    categoryService = new CategoryServiceImpl(categoryRepository);
  }

  @Test
  void shouldReturnAllCategories() {
    //Arrange
    Category category1 = new Category("Category1");
    Category category2 = new Category("Category2");
    when(categoryRepository.findAll()).thenReturn(List.of(category1, category2));

    //Act
    List<Category> actual = categoryService.getAll();

    //Assert
    assertEquals(List.of(category1, category2), actual);
  }

  @Test
  void shouldReturnAllCategoriesLikeName() {
    //Arrange
    Category category1 = new Category("Category1");
    Category category2 = new Category("Category2");
    when(categoryRepository.findAllLikeName("Category")).thenReturn(List.of(category1, category2));

    //Act
    List<Category> actual = categoryService.getAllLikeName("Category");

    //Assert
    assertEquals(List.of(category1, category2), actual);
  }


  @Test
  void shouldReturnCategoryWhenIDExists() throws CategoryNotFoundByIDException {
    //Arrange
    UUID id = UUID.randomUUID();
    Category expected = new Category("Category");
    when(categoryRepository.findById(id)).thenReturn(Optional.of(expected));

    //Act
    Category actual = categoryService.getById(id);
    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void shouldThrowExceptionWhenIDNotExists() {
    //Arrange
    UUID id = UUID.randomUUID();
    when(categoryRepository.findById(id)).thenReturn(Optional.empty());

    //Act
    CategoryNotFoundByIDException exception = assertThrows(
        CategoryNotFoundByIDException.class, () -> categoryService.getById(id));

    //Assert
    assertEquals("Category with id " + id + " not found", exception.getMessage());
  }

  @Test
  void shouldCreateCategoryWhenNameDoesNotExist() throws CategoryAlreadyExistedException {
    //Arrange
    String name = "Category";
    Category expected = new Category(name);
    when(categoryRepository.existsByNameLike(name)).thenReturn(false);
    when(categoryRepository.save(any())).thenReturn(expected);

    //Act
    Category actual = categoryService.create(name);

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void shouldThrowExceptionWhenNameExistsWhenCreateCategory() {
    //Arrange
    String name = "Category";
    when(categoryRepository.existsByNameLike(name)).thenReturn(true);

    //Act
    CategoryAlreadyExistedException exception = assertThrows(
        CategoryAlreadyExistedException.class, () -> categoryService.create(name));

    //Assert
    assertEquals("Category with name " + name + " has already existed", exception.getMessage());
  }

  @Test
  void shouldUpdateCategoryNameWhenIDExistsAndNameDoesNotExist()
      throws CategoryNotFoundByIDException, CategoryAlreadyExistedException {
    //Arrange
    UUID id = UUID.randomUUID();
    String name = "Category";
    Category expected = new Category(name);
    when(categoryRepository.existsByNameLike(name)).thenReturn(false);
    when(categoryRepository.findById(id)).thenReturn(Optional.of(expected));
    when(categoryRepository.save(expected)).thenReturn(expected);

    //Act
    Category actual = categoryService.updateName(id, name);

    //Assert
    assertEquals(expected, actual);
  }

  @Test
  void shouldThrowExceptionWhenNameExistsWhenUpdateCategory() {
    //Arrange
    UUID id = UUID.randomUUID();
    String name = "Category";
    when(categoryRepository.existsByNameLike(name)).thenReturn(true);

    //Act
    CategoryAlreadyExistedException exception = assertThrows(
        CategoryAlreadyExistedException.class, () -> categoryService.updateName(id, name));

    //Assert
    assertEquals("Category with name " + name + " has already existed", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenIDNotExistsWhenUpdateCategory() {
    //Arrange
    UUID id = UUID.randomUUID();
    String name = "Category";
    when(categoryRepository.existsByNameLike(name)).thenReturn(false);
    when(categoryRepository.findById(id)).thenReturn(Optional.empty());

    //Act
    CategoryNotFoundByIDException exception = assertThrows(
        CategoryNotFoundByIDException.class, () -> categoryService.updateName(id, name));

    //Assert
    assertEquals("Category with id " + id + " not found", exception.getMessage());
  }

  @Test
  void shouldDeleteCategoryWhenIDExists() throws CategoryNotFoundByIDException {
    //Arrange
    UUID id = UUID.randomUUID();
    when(categoryRepository.existsById(id)).thenReturn(true);

    //Act
    boolean actual = categoryService.delete(id);

    //Assert
    assertTrue(actual);
  }

  @Test
  void shouldThrowExceptionWhenIDNotExistsWhenDeleteCategory() {
    //Arrange
    UUID id = UUID.randomUUID();
    when(categoryRepository.existsById(id)).thenReturn(false);

    //Act
    CategoryNotFoundByIDException exception = assertThrows(
        CategoryNotFoundByIDException.class, () -> categoryService.delete(id));

    //Assert
    assertEquals("Category with id " + id + " not found", exception.getMessage());
  }
}