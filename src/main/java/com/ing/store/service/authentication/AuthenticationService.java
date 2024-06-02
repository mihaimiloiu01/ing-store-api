package com.ing.store.service.authentication;

import com.ing.store.entity.authentication.User;
import com.ing.store.repository.authentication.AuthenticationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
  private AuthenticationRepository authenticationRepository;
  private PasswordEncoder passwordEncoder;

  public User saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return authenticationRepository.save(user);
  }

  public User findByUsername(String username) {
    return authenticationRepository.findByUsername(username);
  }
}
