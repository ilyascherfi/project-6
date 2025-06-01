package com.openclassrooms.mdd.controller;

import com.openclassrooms.mdd.model.dto.ThemeSubscriptionRequest;
import com.openclassrooms.mdd.model.dto.auth.ModifyNoPassword;
import com.openclassrooms.mdd.model.dto.auth.ModifyRequest;
import com.openclassrooms.mdd.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * This modifies the user's credential in database
   * @param modifyRequest an object containing the user's id(Integer), username(String), email(String), password(String)
   * @return Http200 response with String in body
   */
  @PutMapping(value= "/password", consumes="application/json")
  public ResponseEntity<?> putUser(@Valid@RequestBody ModifyRequest modifyRequest) {
    userService.putUser(modifyRequest);
    return ResponseEntity.ok("User Modification Successfull");
  }

  @PutMapping(value= "/noPassword", consumes="application/json")
  public ResponseEntity<?> putUserNoPassword(@Valid@RequestBody ModifyNoPassword modifyRequest) {
    userService.putUserNoPassword(modifyRequest);
    return ResponseEntity.ok("User Modification Successfull");
  }


  @PutMapping(value = "/subscribe/{themeId}")
  public ResponseEntity<String> subscribeToTheme(@PathVariable Long themeId, Authentication authentication) {
    userService.addThemeToUser(authentication.getName(), Math.toIntExact(themeId));
    return ResponseEntity.ok("Theme added successfully");
  }

  @PutMapping(value = "/unsubscribe/{themeId}")
  public ResponseEntity<String> unsubscribeFromTheme(@PathVariable Long themeId, Authentication authentication) {
    userService.removeThemeToUser(authentication.getName(), Math.toIntExact(themeId));
    return ResponseEntity.ok("Theme removed successfully");
  }
}
