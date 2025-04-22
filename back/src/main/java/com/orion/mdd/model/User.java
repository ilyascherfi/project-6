package com.orion.mdd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer userId;
  @Column(unique = true, name = "username")
  @NotNull
  private String username;
  @Column(unique = true, name = "email")
  @NotNull
  private String email;
  @Column(name = "password")
  @NotNull
  private String password;
  @OneToMany(                             //Unidirectional
          cascade = CascadeType.DETACH,
          fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private List<Theme> themes = new ArrayList<>();

  public User() {
  }

}