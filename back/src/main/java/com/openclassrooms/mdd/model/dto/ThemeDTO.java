package com.openclassrooms.mdd.model.dto;

import lombok.Data;

@Data
public class ThemeDTO {
  private Integer themeId;

  private String title;

  private String description;

  public ThemeDTO() {
  }

  public ThemeDTO(Integer themeId, String title, String description) {
    this.themeId = themeId;
    this.title = title;
    this.description = description;
  }
}
