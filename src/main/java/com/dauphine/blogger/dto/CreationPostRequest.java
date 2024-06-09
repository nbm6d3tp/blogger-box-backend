package com.dauphine.blogger.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;

public class CreationPostRequest {

  @Schema(description = "Title of the post", name = "title",
      type = "string", example = "My First Post")
  String title;

  @Schema(description = "Content of the post", name = "content",
      type = "string", example = "This is the content of my first post.")
  String content;

  @Schema(description = "ID of the category the post belongs to", name = "categoryID",
      type = "int", example = "1")
  UUID categoryID;

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
