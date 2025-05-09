package com.openclassrooms.mdd.controller;

import com.openclassrooms.mdd.model.dto.PostArticleRequest;
import com.openclassrooms.mdd.model.dto.ReturnArticleDTO;
import com.openclassrooms.mdd.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
  private ArticleService articleService;

  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @PostMapping
  public ResponseEntity<String> addArticle(Authentication authentication, @Valid @RequestBody PostArticleRequest postArticleRequest) {
    articleService.addArticle(postArticleRequest, authentication.getName());
    return ResponseEntity.ok("L'article a été bien créé");
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReturnArticleDTO> getArticlesByThemes(@PathVariable("id") Integer id) {
    ReturnArticleDTO articleDTO = articleService.getArticleById(id);
    return ResponseEntity.ok(articleDTO);
  }
}
