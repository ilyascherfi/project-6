package com.openclassrooms.mdd.repository;

import com.openclassrooms.mdd.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
  List<Article> findByThemeId(Integer themeId);
}
