package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Post;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, UUID> {

  List<Post> findAllByCategoryId(UUID uuid);

  @Query("""
            SELECT post
            FROM Post post
            Where UPPER(post.title) Like UPPER(Concat('%', :value, '%'))
                  Or UPPER(post.content) Like UPPER(Concat('%', :value, '%'))
      """)
  List<Post> findAllLikeTitleOrContent(@Param("value") String value);

}
