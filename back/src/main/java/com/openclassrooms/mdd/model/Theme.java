package com.openclassrooms.mdd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "themes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String description;

  @ManyToMany(mappedBy = "subscriptions")
  @ToString.Exclude
  @JsonIgnore
  private List<User> subscribers;

  @OneToMany(mappedBy = "theme")
  @ToString.Exclude
  @JsonIgnore
  private List<Article> articles;
}
