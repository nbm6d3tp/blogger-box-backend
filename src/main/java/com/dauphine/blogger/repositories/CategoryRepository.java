package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Category;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

  @Query(
      """
          Select category
          From Category category
          Where UPPER(category.name) Like UPPER(Concat('%', :name, '%'))
          """
  )
  List<Category> findAllLikeName(@Param("name") String name);
}
