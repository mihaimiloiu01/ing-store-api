package com.ing.store.service.authentication;

import com.ing.store.controller.authentication.models.AuthenticationRequest;
import com.ing.store.exception.models.UserNotFoundException;
import com.ing.store.repository.authentication.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
  @Mock
  private UserRepository userRepository;
  @InjectMocks
  private UserService userService;

  @Test
  void testLogin_UserNotFound() {
    AuthenticationRequest authRequest =
        new AuthenticationRequest("nonExistingUsername", "password".toCharArray());

    Assertions.assertThrows(UserNotFoundException.class, () -> {
      userService.login(authRequest);
    });
  }
}
