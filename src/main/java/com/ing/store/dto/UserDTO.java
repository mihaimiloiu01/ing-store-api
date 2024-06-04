package com.ing.store.dto;


import com.ing.store.entity.authentication.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
  private Integer id;
  private String firstName;
  private String lastName;
  private String username;
  private String token;
  private Role role;
}
