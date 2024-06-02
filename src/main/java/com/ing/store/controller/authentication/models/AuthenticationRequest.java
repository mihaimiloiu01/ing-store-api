package com.ing.store.controller.authentication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
  @JsonProperty
  private String username;
  @JsonProperty
  private String password;
}
