package com.ing.store.repository;

import com.ing.store.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findProductById(Integer id);
  Optional<Product> findProductByName(String name);
}
