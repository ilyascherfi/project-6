package com.openclassrooms.mdd.repository;

import com.openclassrooms.mdd.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
