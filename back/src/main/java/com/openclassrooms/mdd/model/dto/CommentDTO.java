package com.openclassrooms.mdd.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDTO {
  private String author;
  private String content;

}
