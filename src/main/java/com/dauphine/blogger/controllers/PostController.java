package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CreationPostRequest;
import com.dauphine.blogger.dto.UpdatePostRequest;
import com.dauphine.blogger.exeptions.CategoryNotFoundByIDException;
import com.dauphine.blogger.exeptions.PostNotFoundByIDException;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
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
  @Operation(summary = "Get all posts endpoint", description = "Retrieve all posts, or filter like title or content if param 'value' is provided", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of posts"),
  })
  public ResponseEntity<List<Post>> getAll(
      @Parameter(description = "Value to filter like title or content") @RequestParam(required = false) String value) {
    return ResponseEntity.ok(value == null || value.isBlank() ? postService.getAll()
        : postService.getAllLikeTitleOrContent(value))
        ;
  }

  @GetMapping("/orderedByCreationDate")
  @Operation(summary = "Get all posts ordered by creation date endpoint", description = "Retrieve all posts ordered by creation date", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of posts"),
  })
  public ResponseEntity<List<Post>> getAllOrderedByCreationDate() {
    return ResponseEntity.ok(postService.getAllOrderedByCreationDate());
  }

  @GetMapping("/category/{id}")
  @Operation(summary = "Get all posts by category endpoint", description = "Retrieve all posts by category", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of posts"),
      @ApiResponse(responseCode = "404", description = "Posts not found for the given category ID")})
  public ResponseEntity<List<Post>> getAllByCategory(
      @Parameter(description = "ID of the category to retrieve posts from") @PathVariable UUID id)
      throws CategoryNotFoundByIDException {
    return ResponseEntity.ok(postService.getAllByCategoryId(id));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a post by ID endpoint", description = "Retrieve a post by ID", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved the post"),
      @ApiResponse(responseCode = "404", description = "Post not found for the given ID")
  })
  public ResponseEntity<Post> getByID(
      @Parameter(description = "ID Post to get") @PathVariable UUID id)
      throws PostNotFoundByIDException {
    return ResponseEntity.ok(postService.getById(id));
  }

  @PostMapping
  @Operation(summary = "Create a post endpoint", description = "Create a post", responses = {
      @ApiResponse(responseCode = "201", description = "Successfully created the post"),
      @ApiResponse(responseCode = "404", description = "Category not found for the given ID in the request body")
  })
  public ResponseEntity<Post> create(
      @Parameter(description = "Request body to create a new post") @RequestBody CreationPostRequest body)
      throws CategoryNotFoundByIDException {
    Post post = postService.create(body.getTitle(), body.getContent(), body.getCategoryID());
    return ResponseEntity.created(
            URI.create("v1/posts/" + post.getId())).
        body(post);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update a post endpoint", description = "Update an existing post", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully updated the post"),
      @ApiResponse(responseCode = "404", description = "Post or category not found for the given ID")
  })
  public ResponseEntity<Post> put(
      @Parameter(description = "ID Post to update") @PathVariable UUID id,
      @RequestBody UpdatePostRequest body)
      throws PostNotFoundByIDException, CategoryNotFoundByIDException {
    return ResponseEntity.ok(
        postService.put(id, body.getTitle(), body.getContent(), body.getCategoryID()));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a post endpoint", description = "Delete an existing post", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully deleted the post"),
      @ApiResponse(responseCode = "404", description = "Post not found for the given ID")
  })
  public ResponseEntity<?> delete(
      @Parameter(description = "ID Post to delete") @PathVariable UUID id)
      throws PostNotFoundByIDException {
    return ResponseEntity.ok(postService.delete(id));
  }
}