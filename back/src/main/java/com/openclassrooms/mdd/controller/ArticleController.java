package com.openclassrooms.mdd.controller;

import com.openclassrooms.mdd.configuration.CustomUserDetailsService;
import com.openclassrooms.mdd.model.Theme;
import com.openclassrooms.mdd.model.User;
import com.openclassrooms.mdd.model.dto.PostArticleRequest;
import com.openclassrooms.mdd.model.dto.ReturnArticleDTO;
import com.openclassrooms.mdd.service.ArticleService;
import com.openclassrooms.mdd.service.SubscriptionService;
import com.openclassrooms.mdd.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
  private final ArticleService articleService;
  private final CustomUserDetailsService customUserDetailsService;
  private final UserService userService;
  private final SubscriptionService subscriptionService;

  public ArticleController(ArticleService articleService, CustomUserDetailsService customUserDetailsService, UserService userService, SubscriptionService subscriptionService) {
    this.articleService = articleService;
    this.customUserDetailsService = customUserDetailsService;
    this.userService = userService;
    this.subscriptionService = subscriptionService;
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

  @GetMapping("/get")
  public List<ReturnArticleDTO> getArticles() {
    // get the user
    Long userId = customUserDetailsService.getCurrentUserId();
    User user = userService.findById(userId.intValue());

    // get its subscriptions
    List<Theme> themes = subscriptionService.getSubscriptions(user);
    return articleService.getArticleByTheme(themes);
  }
}
