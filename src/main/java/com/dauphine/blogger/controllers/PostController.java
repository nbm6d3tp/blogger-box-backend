package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Post API", description = "Post endpoints")
@RequestMapping("/v1/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  @Operation(summary = "Get all posts endpoint", description = "Retrieve all posts")
  public String getAll() {
    StringBuilder result = new StringBuilder("All posts:<br/>");
    for (Post post : postService.getAll()) {
      result.append(post.toString()).append("<br/>");
    }
    return result.toString();
  }

  @GetMapping("/orderedByCreationDate")
  @Operation(summary = "Get all posts ordered by creation date endpoint", description = "Retrieve all posts ordered by creation date")
  public String getAllOrderedByCreationDate() {
    StringBuilder result = new StringBuilder("All posts ordered by creation date:<br/>");
    for (Post post : postService.getAllOrderedByCreationDate()) {
      result.append(post.toString()).append("<br/>");
    }
    return result.toString();
  }

  @GetMapping("/category/{id}")
  @Operation(summary = "Get all posts by category endpoint", description = "Retrieve all posts by category")
  public String getAllByCategory(@PathVariable UUID id) {
    StringBuilder result = new StringBuilder("All posts by category:<br/>");
    for (Post post : postService.getAllByCategory(id)) {
      result.append(post.toString()).append("<br/>");
    }
    if (result.toString().equals("All posts by category:<br/>")) {
      return "No posts found for category of ID " + id;
    }
    return result.toString();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a post by ID endpoint", description = "Retrieve a post by ID")
  public String getByID(@Parameter(description = "ID Post to get") @PathVariable UUID id) {
    StringBuilder result = new StringBuilder("Post by ID " + id + ":<br/>");
    Optional<Post> post = postService.getById(id);
    if (post.isEmpty()) {
      return "No post found for ID " + id;
    }
    result.append(post.get().toString()).append("<br/>");
    return result.toString();
  }

  @PostMapping
  @Operation(summary = "Create a post endpoint", description = "Create a post")
  public String create(@RequestBody CreationPostRequest body) {
    return "Create new post";
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a post endpoint", description = "Update an existing post")
  public String put(@Parameter(description = "ID Post to update") @PathVariable Integer id,
      @RequestBody UpdatePostRequest body) {
    return "Update an existing post of ID " + id;
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a post endpoint", description = "Delete an existing post")
  public String delete(@Parameter(description = "ID Post to delete") @PathVariable Integer id) {
    return "Delete post of id " + id;
  }
}