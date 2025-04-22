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
  private Long id;
  private String username;
  private String email;
  private String password;

  @ManyToMany
  @JoinTable(
          name = "user_theme",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "theme_id"))
  private List<Theme> themes;

  @OneToMany(mappedBy = "user")
  private List<Article> articles;

  @OneToMany(mappedBy = "user")
  private List<Comment> comments;

}