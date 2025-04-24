package com.openclassrooms.mdd.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
  Long id;
  String username;
  String email;
  String password;
  LocalDate created_at;
  LocalDate updated_at;
}
