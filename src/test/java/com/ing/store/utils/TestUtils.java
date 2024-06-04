package com.ing.store.utils;

import com.ing.store.dto.SignupDTO;
import com.ing.store.dto.UserDTO;
import com.ing.store.entity.authentication.Role;
import com.ing.store.entity.authentication.User;
import com.ing.store.entity.product.Product;
import org.springframework.security.core.parameters.P;

public class TestUtils {
  public static Product generateProduct() {
    Product product = new Product();
    product.setName("MockedProduct");
    product.setPrice(14000.00);
    product.setCurrency("RON");
    return product;
  }

  public static UserDTO generateUserDTO() {
    return UserDTO.builder()
        .firstName("MockedFirstName")
        .lastName("MockedLastName")
        .role(Role.USER)
        .username("MockedUsername")
        .token("MockedToken")
        .build();
  }

  public static SignupDTO generateSignupDTO() {
    return SignupDTO.builder()
        .firstName("MockedFirstName")
        .lastName("MockedLastName")
        .username("MockedUsername")
        .password("MockedPassword".toCharArray())
        .build();
  }

  public static User generateUser() {
    return User.builder()
        .firstName("MockedFirstName")
        .lastName("MockedLastName")
        .role(Role.USER)
        .username("MockedUsername")
        .build();
  }
}
