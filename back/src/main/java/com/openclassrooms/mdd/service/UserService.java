package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.model.Theme;
import com.openclassrooms.mdd.model.User;
import com.openclassrooms.mdd.model.dto.ThemeDTO;
import com.openclassrooms.mdd.model.dto.auth.LoginResponse;
import com.openclassrooms.mdd.model.dto.auth.ModifyNoPassword;
import com.openclassrooms.mdd.model.dto.auth.ModifyRequest;
import com.openclassrooms.mdd.model.dto.auth.RegisterRequest;
import com.openclassrooms.mdd.repository.ThemeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.openclassrooms.mdd.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder;
  private final ModelMapper modelMapper;
  private final ThemeRepository themeRepository;

  public UserService(UserRepository userRepository,ThemeRepository themeRepository, BCryptPasswordEncoder encoder, ModelMapper modelMapper){
    this.userRepository = userRepository;
    this.encoder = encoder;
    this.modelMapper = modelMapper;
    this.themeRepository = themeRepository;
  }

  public Boolean usernameExistsInDB(String username){
    List<User> users = userRepository.findByUsername(username); //usernames are unique
    return !users.isEmpty();
  }
  public Boolean emailExistsInDB(String email){
    List<User> users = userRepository.findByEmail(email); //emails are unique
    return !users.isEmpty();
  }
  public User findByUsernameOrEmail(String usernameOrEmail){
    List<User> user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    return user.getFirst();
  }

  public LoginResponse findByEmail(String email){
    User user = userRepository.findByEmail(email).getFirst();
    ThemeDTO[] themes = user.getSubscriptions().stream()
            .map((element) -> modelMapper.map(element, ThemeDTO.class))
            .toArray(size -> new ThemeDTO[size]);
    return new LoginResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            themes
    );
  }
  public void registerUser(RegisterRequest registerRequest){
    User user = new User();
    user.setUsername(registerRequest.username());
    user.setEmail(registerRequest.email());
    user.setPassword(encoder.encode(registerRequest.password()));
    userRepository.save(user);
  }

  public void putUser(ModifyRequest request){
    User user = userRepository.findById(request.getId()).get();
    user.setEmail(request.getEmail());
    user.setUsername(request.getUsername());
    user.setPassword(encoder.encode(request.getPassword()));
    userRepository.save(user);
  }
  public void putUserNoPassword(ModifyNoPassword request){
    User user = userRepository.findById(request.getId()).get();
    user.setEmail(request.getEmail());
    user.setUsername(request.getUsername());
    userRepository.save(user);
  }

  public void addThemeToUser(String email, Integer themeId) {
    User user = userRepository.findByEmail(email).getFirst();
    Theme theme = themeRepository.findById(themeId).get();
    user.getSubscriptions().add(theme);
    userRepository.save(user);
  }
  public void removeThemeToUser(String email, Integer themeId) {
    User user = userRepository.findByEmail(email).getFirst();
    Theme themeToRemove = themeRepository.findById(themeId).get();
    List<Theme> updatedThemes = user.getSubscriptions().stream().filter(
                    (theme) -> !theme.getId().equals(themeToRemove.getId()))
            .toList();
    ArrayList<Theme> updatedThemesArraylist = new ArrayList<>(updatedThemes);
    user.setSubscriptions(updatedThemesArraylist);
    userRepository.save(user);
  }

}
