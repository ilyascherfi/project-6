package com.openclassrooms.mdd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
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
  @ToString.Exclude
  @JsonIgnore
  private List<Theme> subscriptions = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  @ToString.Exclude
  @JsonIgnore
  private List<Article> articles;

  @OneToMany(mappedBy = "user")
  @ToString.Exclude
  @JsonIgnore
  private List<Comment> comments;
}
