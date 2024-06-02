package com.ing.store.repository.authentication;

import com.ing.store.entity.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<User, Integer> {
  User findByUsername(String username);
}
