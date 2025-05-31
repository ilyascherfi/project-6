package com.openclassrooms.mdd.configuration;

import com.openclassrooms.mdd.model.User;
import com.openclassrooms.mdd.model.dto.auth.LoginResponse;
import com.openclassrooms.mdd.repository.UserRepository;
import com.openclassrooms.mdd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserService userService;
  private UserRepository userRepository;
  public CustomUserDetailsService(UserService userService, UserRepository userRepository) {
    this.userService = userService;
    this.userRepository = userRepository;
  }
  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    List<User> user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    if (user.size() != 1) {
      throw new UsernameNotFoundException("User not found with Username or Email: " + usernameOrEmail);
    }
    return new org.springframework.security.core.userdetails.User(user.getFirst().getEmail(), user.getFirst().getPassword(), new ArrayList<>()); //empty GrantedAuthorities
  }

  public Long getCurrentUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Jwt jwt = (Jwt) authentication.getPrincipal();

    // 1. Vérification complète du JWT
    Object subClaim = jwt.getClaims().get("sub");
    System.out.println("Sub claim content: " + subClaim);

    // 2. Extraction robuste de l'email
    String email;
    if (subClaim instanceof Map) {
      // Cas où "sub" est un objet JSON complexe
      email = ((Map<?,?>)subClaim).get("email").toString();
    } else {
      // Cas normal où "sub" est une string directe
      email = subClaim.toString();
    }

    System.out.println("Extracted email: " + email); // Vérifiez dans la console

    // 3. Debuggage pointu
    LoginResponse user = userService.findByEmail(email);
    System.out.println("User found: " + user); // Vérifiez que le user est correct

    return user.getId();
  }
}