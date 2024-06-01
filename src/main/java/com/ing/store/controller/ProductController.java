package com.ing.store.controller;

import com.ing.store.entity.Product;
import com.ing.store.service.ProductService;
import com.ing.store.utils.Constants;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
@AllArgsConstructor
@Slf4j
public class ProductController {
  private ProductService productService;

  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    return ResponseEntity.ok(productService.addProduct(product));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> findProductById(@PathVariable Integer id) {
    return ResponseEntity.ok(productService.findProductById(id));
  }

  @GetMapping
  public ResponseEntity<List<Product>> retrieveAllProducts() {
    return ResponseEntity.ok(productService.retrieveAllProducts());
  }

  @PutMapping("/{id}/price")
  public ResponseEntity<Product> updateProductPrice(@PathVariable Integer id, @RequestParam Double price) {
    return ResponseEntity.ok(productService.updateProductPrice(id, price));
  }
}
