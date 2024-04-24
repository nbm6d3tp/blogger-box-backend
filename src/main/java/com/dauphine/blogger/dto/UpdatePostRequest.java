package com.dauphine.blogger.dto;


import io.swagger.v3.oas.annotations.media.Schema;

public class UpdatePostRequest {

  @Schema(description = "Updated title of the post", name = "title",
      type = "string", example = "My Updated Post")
  String title;

  @Schema(description = "Updated content of the post", name = "content",
      type = "string", example = "This is the updated content of my post.")
  String content;

  @Schema(description = "Updated creation date of the post", name = "creationDate",
      type = "string", example = "2022-01-02")
  String creationDate;

  @Schema(description = "ID of the updated category the post belongs to", name = "category_id",
      type = "int", example = "2")
  int category_id;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getCategory_id() {
    return category_id;
  }

  public void setCategory_id(int category_id) {
    this.category_id = category_id;
  }
}