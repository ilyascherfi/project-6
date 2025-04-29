package com.openclassrooms.mdd.controller;

import com.openclassrooms.mdd.model.dto.ThemeDTO;
import com.openclassrooms.mdd.service.ThemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/theme")
public class ThemeController {
  private ThemeService themeService;

  public ThemeController(ThemeService themeService) {
    this.themeService = themeService;
  }

  @GetMapping("")
  public ResponseEntity<ThemeDTO[]> getAllThemes() {
    ThemeDTO[] themes = themeService.getAllThemes();
    return ResponseEntity.ok(themes);
  }
}
