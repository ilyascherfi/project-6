package com.openclassrooms.mdd.configuration;

import com.openclassrooms.mdd.model.User;
import com.openclassrooms.mdd.model.dto.auth.LoginResponse;
import com.openclassrooms.mdd.repository.UserRepository;
import com.openclassrooms.mdd.service.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final IUserService userService;
  private final UserRepository userRepository;

  public CustomUserDetailsService(IUserService userService, UserRepository userRepository) {
    this.userService = userService;
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    List<User> user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found with Username or Email: " + usernameOrEmail);
    }
    return new org.springframework.security.core.userdetails.User(user.getFirst().getEmail(), user.getFirst().getPassword(), new ArrayList<>());
  }

  public Long getCurrentUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = ((UserDetails) authentication.getPrincipal()).getUsername();
    UserDetails userDetails = loadUserByUsername(username);
    LoginResponse user = userService.findByEmail(userDetails.getUsername());
    return user.getId();
  }
}
