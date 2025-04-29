package com.openclassrooms.mdd.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostComment {
  @NotBlank
  private String content;
  @NotNull
  private Integer articleId;
}
