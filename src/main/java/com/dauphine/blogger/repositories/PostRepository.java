package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Post;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

  List<Post> findAllByCategoryId(UUID uuid);

}
