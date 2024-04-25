package com.dauphine.blogger.repositories;

import com.dauphine.blogger.models.Post;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, UUID> {

}
