package com.openclassrooms.mdd.model.dto;

import lombok.Data;

@Data
public class CommentDTO {
  private String author;
  private String content;

  public CommentDTO() {
  }

  public CommentDTO(String author, String content) {
    this.author = author;
    this.content = content;
  }
}
