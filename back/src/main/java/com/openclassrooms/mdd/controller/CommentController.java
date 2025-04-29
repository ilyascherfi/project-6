package com.openclassrooms.mdd.controller;

import com.openclassrooms.mdd.model.dto.PostComment;
import com.openclassrooms.mdd.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
  private CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping("")
  public ResponseEntity<?> postComment(@RequestBody @Valid PostComment comment, Authentication authentication) {
    String email = authentication.getName();
    commentService.addComment(comment, email);
    return ResponseEntity.ok("Comment added successfully");
  }

}
