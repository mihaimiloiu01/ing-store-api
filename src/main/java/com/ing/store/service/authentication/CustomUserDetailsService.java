package com.ing.store.service.authentication;

import com.ing.store.entity.authentication.User;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private AuthenticationService authenticationService;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = authenticationService.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        new ArrayList<>());
  }
}
