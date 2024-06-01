package com.ing.store.service;

import com.ing.store.entity.Product;
import com.ing.store.exception.ProductNotFoundException;
import com.ing.store.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public Product addProduct(Product product) {
    return productRepository.save(product);
  }

  public Product findProductById(Long id) {
    return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }

  public List<Product> retrieveAllProducts() {
    return productRepository.findAll();
  }

  public Product updateProductPrice(Long id, BigDecimal newPrice) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));
    product.setPrice(newPrice);
    return productRepository.save(product);
  }
}
