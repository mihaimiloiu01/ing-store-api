package com.ing.store.controller;

import com.ing.store.entity.Product;
import com.ing.store.service.ProductService;
import com.ing.store.utils.Constants;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/products")
@Slf4j
public class ProductController {
  @Autowired
  private ProductService productService;

  @PostMapping
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    log.info(Constants.ADDING_PRODUCT_LOG_MESSAGE, product);
    return ResponseEntity.ok(productService.addProduct(product));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> findProductById(@PathVariable UUID id) {
    log.info(Constants.FINDING_PRODUCT_LOG_MESSAGE, id);
    return ResponseEntity.ok(productService.findProductById(id));
  }

  @GetMapping
  public ResponseEntity<List<Product>> retrieveAllProducts() {
    log.info(Constants.RETRIEVE_ALL_PRODUCTS_LOG_MESSAGE);
    return ResponseEntity.ok(productService.retrieveAllProducts());
  }

  @PutMapping("/{id}/price")
  public ResponseEntity<Product> updateProductPrice(@PathVariable UUID id, @RequestParam Double price) {
    log.info(Constants.UPDATING_PRICE_LOG_MESSAGE, id, price);
    return ResponseEntity.ok(productService.updateProductPrice(id, price));
  }
}
