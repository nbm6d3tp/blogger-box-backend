package com.dauphine.blogger.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

public class UpdatePostRequest {

  @Schema(description = "Updated title of the post", name = "title",
      type = "string", example = "My Updated Post")
  String title;

  @Schema(description = "Updated content of the post", name = "content",
      type = "string", example = "This is the updated content of my post.")
  String content;

  @Schema(description = "ID of the updated category the post belongs to", name = "categoryID",
      type = "UUID", example = "2")
  UUID categoryID;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public UUID getCategoryID() {
    return categoryID;
  }

  public void setCategoryID(UUID categoryID) {
    this.categoryID = categoryID;
  }
}