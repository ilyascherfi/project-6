package com.openclassrooms.mdd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="user_theme_subscriptions")
@Data
public class Subscription {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "theme_id")
  private Theme theme;

}
