package com.ing.store.service;

import com.ing.store.entity.Product;
import com.ing.store.exception.ProductNotFoundException;
import com.ing.store.repository.ProductRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
  private ProductRepository productRepository;

  public Product addProduct(Product product) {
    return productRepository.save(product);
  }

  public Product findProductById(Integer id) {
    return productRepository.findProductById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }

  public List<Product> retrieveAllProducts() {
    return productRepository.findAll();
  }

  public Product updateProductPrice(Integer id, Double newPrice) {
    Product product = productRepository.findProductById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));
    product.setPrice(newPrice);
    return productRepository.save(product);
  }
}
