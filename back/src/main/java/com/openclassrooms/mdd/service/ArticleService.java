package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.model.Article;
import com.openclassrooms.mdd.model.Theme;
import com.openclassrooms.mdd.model.User;
import com.openclassrooms.mdd.model.dto.CommentDTO;
import com.openclassrooms.mdd.model.dto.PostArticleRequest;
import com.openclassrooms.mdd.model.dto.ReturnArticleDTO;
import com.openclassrooms.mdd.repository.ArticleRepository;
import com.openclassrooms.mdd.repository.ThemeRepository;
import com.openclassrooms.mdd.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
  private final ArticleRepository articleRepository;
  private final UserRepository userRepository;
  private final ThemeRepository themeRepository;
  private final ModelMapper modelMapper;

  public ArticleService(ArticleRepository articleRepository, UserRepository userRepository, ThemeRepository themeRepository, ModelMapper modelMapper) {
    this.articleRepository = articleRepository;
    this.userRepository = userRepository;
    this.themeRepository = themeRepository;
    this.modelMapper = modelMapper;
  }

  public void addArticle(PostArticleRequest request, String email){
    User user = userRepository.findByEmail(email).getFirst();
    Theme theme = themeRepository.findById(Math.toIntExact(request.theme())).get();
    Article article = new Article();
    article.setUser(user);
    article.setTheme(theme);
    article.setTitle(request.title());
    article.setDate(LocalDate.now());
    article.setContent(request.content());
    articleRepository.save(article);
  }

  public ReturnArticleDTO getArticleById(Integer id) {
    Article article = articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found"));
    return convertToDto(article);
  }

  public List<ReturnArticleDTO> getArticleByTheme(List<Integer> themeIds) {
    List<Article> articles = articleRepository.findByThemeIdIn(themeIds);
    return articles.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
  }

  public List<ReturnArticleDTO> getArticlesByUserId(Integer userId) {
    return articleRepository.findByUserId(userId).stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
  }

  private ReturnArticleDTO convertToDto(Article article) {
    ReturnArticleDTO dto = new ReturnArticleDTO();
    dto.setArticleId(article.getId().longValue());
    dto.setTitle(article.getTitle());
    dto.setContent(article.getContent());
    dto.setDate(article.getDate());

    if (article.getUser() != null) {
      dto.setAuthor(article.getUser().getUsername());
    }

    if (article.getTheme() != null) {
      dto.setTheme(article.getTheme().getTitle());
    }

    if (article.getComments() != null) {
      dto.setComments(article.getComments().stream()
              .map(comment -> {
                CommentDTO commentDto = new CommentDTO();
                commentDto.setContent(comment.getContent());
                if (comment.getUser() != null) {
                  commentDto.setAuthor(comment.getUser().getUsername());
                }
                return commentDto;
              })
              .collect(Collectors.toList()));
    }

    return dto;
  }
}
