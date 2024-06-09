package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  @Operation(summary = "Get all posts endpoint", description = "Retrieve all posts or filter like title or content")
  public List<Post> getAll(@RequestParam(required = false) String value) {
    return postService.getAllLikeTitleOrContent(value);
  }

  @GetMapping("/orderedByCreationDate")
  @Operation(summary = "Get all posts ordered by creation date endpoint", description = "Retrieve all posts ordered by creation date")
  public List<Post> getAllOrderedByCreationDate() {
    return postService.getAllOrderedByCreationDate();
  }

  @GetMapping("/category/{id}")
  @Operation(summary = "Get all posts by category endpoint", description = "Retrieve all posts by category")
  public List<Post> getAllByCategory(@PathVariable UUID id) {
    return postService.getAllByCategoryId(id);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a post by ID endpoint", description = "Retrieve a post by ID")
  public Post getByID(@Parameter(description = "ID Post to get") @PathVariable UUID id) {
    return postService.getById(id);
  }

  @PostMapping
  @Operation(summary = "Create a post endpoint", description = "Create a post")
  public Post create(@RequestBody CreationPostRequest body) {
    return postService.create(body.getTitle(), body.getContent(), body.getCategory_id());
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a post endpoint", description = "Update an existing post")
  public Post put(@Parameter(description = "ID Post to update") @PathVariable UUID id,
      @RequestBody UpdatePostRequest body) {
    return postService.put(id, body.getTitle(), body.getContent(), body.getCategory_id());
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a post endpoint", description = "Delete an existing post")
  public boolean delete(@Parameter(description = "ID Post to delete") @PathVariable UUID id) {
    return postService.delete(id);
  }
}