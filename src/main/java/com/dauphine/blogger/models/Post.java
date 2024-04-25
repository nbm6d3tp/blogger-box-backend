package com.dauphine.blogger.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "post")
public class Post {

  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @Column(name = "title")
  private String title;

  @Column(name = "content")
  private String content;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  public Post() {
  }

  public Post(String title, String content, Category category) {
    this.id = UUID.randomUUID();
    this.title = title;
    this.content = content;
    createdDate = LocalDateTime.now();
    this.category = category;
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

  public LocalDateTime getCreationDate() {
    return createdDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.createdDate = creationDate;
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
        ", creationDate='" + createdDate + '\'' +
        ", category=" + category +
        '}';
  }
}
