package com.orion.mdd.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Theme {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "article_id")
  private Integer articleId;
  private String title;
  @ManyToOne(                              //Bidirectional
          cascade = CascadeType.ALL
  )
  @JoinColumn(name="theme_id")
  private Theme theme;
  @ManyToOne(                              //Unidirectional
          cascade = CascadeType.ALL
  )
  @JoinColumn(name = "user_id")
  private User user;
  private LocalDate date;
  @Size(max = 1000)
  private String content;

}
