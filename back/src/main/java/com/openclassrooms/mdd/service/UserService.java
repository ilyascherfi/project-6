package com.openclassrooms.mdd.service;

import com.openclassrooms.mdd.model.User;
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
    return users.size()>0;
  }
  public Boolean emailExistsInDB(String email){
    List<User> users = userRepository.findByEmail(email); //emails are unique
    return users.size()>0;
  }
  public User findByUsernameOrEmail(String usernameOrEmail){
    List<User> user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    return user.get(0);
  }

}
