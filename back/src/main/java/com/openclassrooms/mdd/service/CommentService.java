package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.model.Article;
import com.openclassrooms.mdd.model.Comment;
import com.openclassrooms.mdd.model.User;
import com.openclassrooms.mdd.model.dto.PostComment;
import com.openclassrooms.mdd.repository.ArticleRepository;
import com.openclassrooms.mdd.repository.CommentRepository;
import com.openclassrooms.mdd.repository.UserRepository;

public class CommentService {
  private CommentRepository commentRepository;
  private UserRepository userRepository;
  private ArticleRepository articleRepository;

  public CommentService(CommentRepository commentRepository, UserRepository userRepository, ArticleRepository articleRepository) {
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
    this.articleRepository = articleRepository;
  }

  public void addComment(PostComment postComment, String email){
    User user = userRepository.findByEmail(email).getFirst(); //Unicity and existence
    Article article = articleRepository.findById(postComment.getArticleId()).get(); //Unicity and existence
    Comment comment = new Comment(article, user, postComment.getContent());
    commentRepository.save(comment);
  }
}
