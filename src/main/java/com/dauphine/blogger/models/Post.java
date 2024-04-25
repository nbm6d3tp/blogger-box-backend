package com.dauphine.blogger.models;

import java.util.UUID;

public class Post {

  private UUID id;
  private String title;
  private String content;
  private String creationDate;
  private Category category;

  public Post(UUID id, String title, String content, String creationDate, Category category) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.category = category;

//    this.creationDate = Instant.now().toEpochMilli()
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

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

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", creationDate='" + creationDate + '\'' +
        ", category=" + category +
        '}';
  }
}
