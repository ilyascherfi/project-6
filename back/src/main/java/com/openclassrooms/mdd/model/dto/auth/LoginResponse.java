package com.openclassrooms.mdd.model.dto.auth;

import com.openclassrooms.mdd.model.dto.ThemeDTO;
import lombok.Data;

@Data
public class LoginResponse {
  private Long id;
  private String username;
  private String email;
  private ThemeDTO[] themes;

  public LoginResponse(Long id, String username, String email, ThemeDTO[] themes){
    this.id = id;
    this.username = username;
    this.email = email;
    this.themes = themes;
  }
}
