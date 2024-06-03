package com.ing.store.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupDTO {

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @NotEmpty
  private String username;

  @NotEmpty
  private char[] password;
}
