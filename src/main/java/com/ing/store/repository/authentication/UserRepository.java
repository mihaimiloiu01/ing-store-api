package com.ing.store.repository.authentication;

import com.ing.store.entity.authentication.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUsername(String username);
}
