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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
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
      log.error("ERROR: Username already in use!");
      throw new ExistingCredentialException("Username already in use!");
    }
    log.info("Username available. New user will be created");
    log.info("Mapping DTO info passed in the payload to actual user");
    User user = userMapper.signUpToUser(newUserDto);
    log.info("Setting role as USER");
    user.setRole(Role.USER);
    log.info("Encrypting password");
    user.setPassword(passwordEncoder.encode(CharBuffer.wrap(newUserDto.getPassword())));

    log.info("Attempting to store the user in the database");
    User newUser = userRepository.save(user);
    log.info("User registered successfully");

    return userMapper.toUserDto(newUser);
  }

  public UserDTO findByUsername(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException("User was not found!"));
    return userMapper.toUserDto(user);
  }
}
