package com.ing.store.entity.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @Column(name = "first_name")
  @JsonProperty
  private String firstName;
  @Column(name = "last_name")
  @JsonProperty
  private String lastName;
  @Column(name = "username", nullable = false)
  @JsonProperty
  private String username;
  @Column(name = "password", nullable = false)
  @JsonProperty
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;
}
