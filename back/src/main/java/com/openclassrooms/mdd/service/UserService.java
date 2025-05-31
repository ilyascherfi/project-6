package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.model.Theme;
import com.openclassrooms.mdd.model.User;
import com.openclassrooms.mdd.model.dto.ThemeDTO;
import com.openclassrooms.mdd.model.dto.auth.LoginResponse;
import com.openclassrooms.mdd.model.dto.auth.ModifyNoPassword;
import com.openclassrooms.mdd.model.dto.auth.ModifyRequest;
import com.openclassrooms.mdd.model.dto.auth.RegisterRequest;
import com.openclassrooms.mdd.repository.ThemeRepository;
import com.openclassrooms.mdd.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder;
  private final ModelMapper modelMapper;
  private final ThemeRepository themeRepository;

  public UserService(UserRepository userRepository, ThemeRepository themeRepository, BCryptPasswordEncoder encoder, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.encoder = encoder;
    this.modelMapper = modelMapper;
    this.themeRepository = themeRepository;
  }

  public Boolean usernameExistsInDB(String username) {
    List<User> users = userRepository.findByUsername(username);
    return !users.isEmpty();
  }

  public Boolean emailExistsInDB(String email) {
    Optional<User> users = userRepository.findByEmail(email);
    return !users.isEmpty();
  }

  public User findByUsernameOrEmail(String usernameOrEmail) {
    List<User> user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    return user.getFirst();
  }

  @Override
  public LoginResponse findByEmail(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    ThemeDTO[] themes = user.getSubscriptions().stream()
            .map((element) -> modelMapper.map(element, ThemeDTO.class))
            .toArray(ThemeDTO[]::new);
    return new LoginResponse(user.getId(), user.getUsername(), user.getEmail(), themes);
  }

  public void registerUser(RegisterRequest registerRequest) {
    User user = new User();
    user.setUsername(registerRequest.username());
    user.setEmail(registerRequest.email());
    user.setPassword(encoder.encode(registerRequest.password()));
    userRepository.save(user);
  }

  public void putUser(ModifyRequest request) {
    User user = userRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("User not found"));
    user.setEmail(request.getEmail());
    user.setUsername(request.getUsername());
    user.setPassword(encoder.encode(request.getPassword()));
    userRepository.save(user);
  }

  public void putUserNoPassword(ModifyNoPassword request) {
    User user = userRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("User not found"));
    user.setEmail(request.getEmail());
    user.setUsername(request.getUsername());
    userRepository.save(user);
  }

  public void addThemeToUser(String email, Integer themeId) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("Theme not found"));
    user.getSubscriptions().add(theme);
    userRepository.save(user);
  }

  public void removeThemeToUser(String email, Integer themeId) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    Theme themeToRemove = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("Theme not found"));
    List<Theme> updatedThemes = user.getSubscriptions().stream()
            .filter((theme) -> !theme.getId().equals(themeToRemove.getId()))
            .toList();
    user.setSubscriptions(new ArrayList<>(updatedThemes));
    userRepository.save(user);
  }

  public User findById(Integer id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }
}
