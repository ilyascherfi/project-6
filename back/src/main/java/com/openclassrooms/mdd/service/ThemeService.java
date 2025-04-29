package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.model.Theme;
import com.openclassrooms.mdd.model.dto.ThemeDTO;
import com.openclassrooms.mdd.repository.ThemeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {
  private final ThemeRepository themeRepository;
  private final ModelMapper modelMapper;

  public ThemeService(ThemeRepository themeRepository, ModelMapper modelMapper) {
    this.themeRepository = themeRepository;
    this.modelMapper = modelMapper;
  }

  public ThemeDTO[] getAllThemes(){
    List<Theme> themes = themeRepository.findAll();
    ThemeDTO[] themesDTO = themes.stream()
            .map((element) -> modelMapper.map(element, ThemeDTO.class))
            .toArray(size -> new ThemeDTO[size]);
    return themesDTO;
  }
}
