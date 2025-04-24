package com.openclassrooms.mdd.controller;

import com.openclassrooms.mdd.model.dto.auth.RegisterRequest;
import com.openclassrooms.mdd.service.JWTService;
import com.openclassrooms.mdd.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
      return new ResponseEntity<String>("Username already taken", HttpStatus.BAD_REQUEST);
    }
    if(userService.emailExistsInDB(registerRequest.email())){
      return new ResponseEntity<String>("Username already taken", HttpStatus.BAD_REQUEST);
    }
    userService.registerUser(registerRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
