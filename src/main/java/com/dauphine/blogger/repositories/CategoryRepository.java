package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Category;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
