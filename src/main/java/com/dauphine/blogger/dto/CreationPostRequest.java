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

  @Schema(description = "Creation date of the post", name = "creationDate",
      type = "string", example = "2022-01-01")
  String creationDate;

  @Schema(description = "ID of the category the post belongs to", name = "category_id",
      type = "int", example = "1")
  UUID category_id;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public UUID getCategory_id() {
    return category_id;
  }

  public void setCategory_id(UUID category_id) {
    this.category_id = category_id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
