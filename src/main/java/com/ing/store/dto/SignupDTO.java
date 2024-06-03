package com.ing.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
  @JsonProperty
  private String firstName;

  @NotEmpty
  @JsonProperty
  private String lastName;

  @NotEmpty
  @JsonProperty
  private String username;

  @NotEmpty
  @JsonProperty
  private char[] password;
}
