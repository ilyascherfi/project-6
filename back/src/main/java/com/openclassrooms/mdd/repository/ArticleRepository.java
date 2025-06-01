package com.openclassrooms.mdd.repository;

import com.openclassrooms.mdd.model.Article;
import com.openclassrooms.mdd.model.Theme;
import com.openclassrooms.mdd.model.dto.ReturnArticleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
  List<Article> findByThemeId(Integer themeId);
  List<Article> findByUserId(Integer userId);
  List<Article> findByThemeIn(List<Theme> theme);
}
