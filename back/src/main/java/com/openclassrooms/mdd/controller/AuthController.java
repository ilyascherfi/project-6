package com.openclassrooms.mdd.controller;

import com.openclassrooms.mdd.model.dto.auth.LoginRequest;
import com.openclassrooms.mdd.model.dto.auth.LoginResponse;
import com.openclassrooms.mdd.model.dto.auth.RegisterRequest;
import com.openclassrooms.mdd.model.dto.auth.TokenResponse;
import com.openclassrooms.mdd.service.JWTService;
import com.openclassrooms.mdd.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
  private JWTService jwtService;
  private UserService userService;

  public AuthController(JWTService jwtService, UserService userService) {
    this.jwtService = jwtService;
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<?>  register(@Valid @RequestBody RegisterRequest registerRequest) {
    if(userService.usernameExistsInDB(registerRequest.username())){
      return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
    }
    if(userService.emailExistsInDB(registerRequest.email())){
      return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
    }
    userService.registerUser(registerRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/me")
  public ResponseEntity<LoginResponse> token(Authentication authentication) {
    return ResponseEntity.ok(userService.findByEmail(authentication.getName()));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
    String token = jwtService.authenticate(loginRequest);
    if (!token.isEmpty()){
      return ResponseEntity.ok(new TokenResponse(token));
    }
    else return new ResponseEntity<String>("error", HttpStatus.OK);
  }


}
