package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.model.Article;
import com.openclassrooms.mdd.model.Theme;
import com.openclassrooms.mdd.model.User;
import com.openclassrooms.mdd.model.dto.PostArticleRequest;
import com.openclassrooms.mdd.model.dto.ReturnArticleDTO;
import com.openclassrooms.mdd.repository.ArticleRepository;
import com.openclassrooms.mdd.repository.ThemeRepository;
import com.openclassrooms.mdd.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ArticleService {
  private ArticleRepository articleRepository;
  private UserRepository userRepository;
  private ThemeRepository themeRepository;
  private ModelMapper modelMapper;

  public ArticleService(ArticleRepository articleRepository, UserRepository userRepository, ThemeRepository themeRepository, ModelMapper modelMapper) {
    this.articleRepository = articleRepository;
    this.userRepository = userRepository;
    this.themeRepository = themeRepository;
    this.modelMapper = modelMapper;
  }

  public void addArticle(PostArticleRequest request, String email){
    User user = userRepository.findByEmail(email).getFirst();
    Theme theme = themeRepository.findById(request.theme()).get();
    Article article = new Article();
    article.setUser(user);
    article.setTheme(theme);
    article.setTitle(request.title());
    article.setDate(LocalDate.now());
    article.setContent(request.content());
    articleRepository.save(article);
  }

  public ReturnArticleDTO getArticleById(Integer id) {
    Optional<Article> article = articleRepository.findById(id);
    return modelMapper.map(article, ReturnArticleDTO.class);
  }

}
