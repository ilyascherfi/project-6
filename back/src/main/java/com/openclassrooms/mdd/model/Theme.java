package com.openclassrooms.mdd.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
  private List<User> subscribers;

  @OneToMany(mappedBy = "theme")
  private List<Article> articles;
}
