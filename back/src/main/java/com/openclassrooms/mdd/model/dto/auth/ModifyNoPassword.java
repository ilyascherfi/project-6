package com.openclassrooms.mdd.model.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ModifyNoPassword {
  @NotNull
  private Integer id;
  @NotBlank(message = "Username may not be null")
  private String username;
  @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
          message = "Invalid email")
  private String email;

  public ModifyNoPassword(Integer id, String username, String email) {
    this.id = id;
    this.username = username;
    this.email = email;
  }
}
