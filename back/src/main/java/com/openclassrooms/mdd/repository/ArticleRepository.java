package com.openclassrooms.mdd.repository;

import com.openclassrooms.mdd.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
