package com.ing.store.controller.authentication;

import com.ing.store.service.authentication.UserService;
import com.ing.store.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthenticationControllerTest {
  @Mock
  private UserService userService;

  @InjectMocks
  private AuthenticationController authenticationController;

  @Test
  void testRegister() {
    ResponseEntity<String> responseEntity =
        authenticationController.register(TestUtils.generateSignupDTO());

    Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    Assertions.assertEquals("User created successfully", responseEntity.getBody());
  }
}
