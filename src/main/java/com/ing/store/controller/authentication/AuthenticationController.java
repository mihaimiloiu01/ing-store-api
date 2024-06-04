package com.ing.store.controller.authentication;

import com.ing.store.config.UserAuthenticationProvider;
import com.ing.store.controller.authentication.models.AuthenticationRequest;
import com.ing.store.controller.authentication.models.AuthenticationResponse;
import com.ing.store.dto.SignupDTO;
import com.ing.store.dto.UserDTO;
import com.ing.store.entity.authentication.User;
import com.ing.store.service.authentication.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
  private final UserService userService;
  private final UserAuthenticationProvider userAuthenticationProvider;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest authRequest) {
    UserDTO userDTO = userService.login(authRequest);
    userDTO.setToken(userAuthenticationProvider.createToken(userDTO));

    return ResponseEntity.ok(new AuthenticationResponse(userDTO.getToken()));
  }

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody @Valid SignupDTO signupDTO) {
    userService.register(signupDTO);
    return ResponseEntity.ok("User created successfully");
  }
}
