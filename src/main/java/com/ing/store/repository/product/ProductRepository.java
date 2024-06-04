package com.ing.store.repository.product;

import com.ing.store.entity.product.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findProductById(Integer id);
  Optional<Product> findProductByName(String name);
  void deleteProductById(Integer id);
}
