package com.ing.store.service.authentication;

import com.ing.store.controller.authentication.models.AuthenticationRequest;
import com.ing.store.dto.SignupDTO;
import com.ing.store.dto.UserDTO;
import com.ing.store.entity.authentication.Role;
import com.ing.store.entity.authentication.User;
import com.ing.store.exception.ExistingCredentialException;
import com.ing.store.exception.InvalidPasswordException;
import com.ing.store.exception.UserNotFoundException;
import com.ing.store.mapper.UserMapper;
import com.ing.store.repository.authentication.UserRepository;
import java.nio.CharBuffer;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  public UserDTO login(AuthenticationRequest authenticationRequest) {
    User user = userRepository.findByUsername(authenticationRequest.getUsername())
        .orElseThrow(() -> new UserNotFoundException("User does not exist!"));

    if (passwordEncoder.matches(CharBuffer.wrap(authenticationRequest.getPassword()), user.getPassword())) {
      return userMapper.toUserDto(user);
    }
    else {
      throw new InvalidPasswordException("You introduced an incorrect password. Try again!");
    }
  }

  public UserDTO register(SignupDTO newUserDto) {
    Optional<User> optionalUser = userRepository.findByUsername(newUserDto.getUsername());

    if (optionalUser.isPresent()) {
      throw new ExistingCredentialException("Username already in use!");
    }

    User user = userMapper.signUpToUser(newUserDto);
    user.setRole(Role.USER);
    user.setPassword(passwordEncoder.encode(CharBuffer.wrap(newUserDto.getPassword())));

    return userMapper.toUserDto(userRepository.save(user));
  }

  public UserDTO findByUsername(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException("User was not found!"));
    return userMapper.toUserDto(user);
  }
}
