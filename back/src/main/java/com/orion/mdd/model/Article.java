package com.orion.mdd.model;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Article {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private Date date;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "theme_id")
  private Theme theme;

  @OneToMany(mappedBy = "article")
  private List<Comment> comments;
}
