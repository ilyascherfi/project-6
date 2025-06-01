package com.openclassrooms.mdd.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReturnArticleDTO {
  private Long articleId;
  private String title;
  private String author;
  private String theme;
  private LocalDate date;
  private String content;
}