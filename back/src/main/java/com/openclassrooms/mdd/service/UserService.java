package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.model.User;
import com.openclassrooms.mdd.model.dto.UserDTO;
import com.openclassrooms.mdd.model.dto.auth.LoginResponse;
import com.openclassrooms.mdd.model.dto.auth.RegisterRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.openclassrooms.mdd.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder;
  private final ModelMapper modelMapper;

  public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder, ModelMapper modelMapper){
    this.userRepository = userRepository;
    this.encoder = encoder;
    this.modelMapper = modelMapper;
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

  public LoginResponse findByEmailAndReturnsDTO(String email){
    User user = userRepository.findByEmail(email).getFirst();
    return new LoginResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail()
    );
  }
  public void registerUser(RegisterRequest registerRequest){
    User user = new User();
    user.setUsername(registerRequest.username());
    user.setEmail(registerRequest.email());
    user.setPassword(encoder.encode(registerRequest.password()));
    userRepository.save(user);
  }

}
