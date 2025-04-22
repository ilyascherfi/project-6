package com.orion.mdd.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Theme {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String description;

  @ManyToMany(mappedBy = "themes")
  private List<User> users;

  @OneToMany(mappedBy = "theme")
  private List<Article> articles;

}
