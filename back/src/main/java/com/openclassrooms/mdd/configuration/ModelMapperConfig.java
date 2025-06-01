package com.openclassrooms.mdd.configuration;

import com.openclassrooms.mdd.model.Article;
import com.openclassrooms.mdd.model.dto.ReturnArticleDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    // Configuration personnalisée pour le mapping Article -> ReturnArticleDTO
    modelMapper.addMappings(new PropertyMap<Article, ReturnArticleDTO>() {
      @Override
      protected void configure() {
        map().setArticleId(Long.valueOf(source.getId()));
        map().setTitle(source.getTitle());
        map().setAuthor(source.getUser().getId());
        map().setTheme(source.getTheme().getId());
        map().setDate(source.getDate());
        map().setContent(source.getContent());
      }
    });

    return modelMapper;
  }
}

