package com.openclassrooms.mdd.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String email;
  private String password;

  @ManyToMany
  @JoinTable(
          name = "user_theme_subscriptions",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "theme_id"))
  private List<Theme> subscriptions;

  @OneToMany(mappedBy = "user")
  private List<Article> articles;

  @OneToMany(mappedBy = "user")
  private List<Comment> comments;
}
