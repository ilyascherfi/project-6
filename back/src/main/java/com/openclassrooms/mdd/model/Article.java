package com.openclassrooms.mdd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  private String content;
  private LocalDate date;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  @JsonIgnore
  private User user;

  @ManyToOne
  @JoinColumn(name = "theme_id")
  @ToString.Exclude
  private Theme theme;

  @OneToMany(mappedBy = "article")
  @ToString.Exclude
  @JsonIgnore
  private List<Comment> comments;
}
