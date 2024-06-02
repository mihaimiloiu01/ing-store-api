package com.ing.store.controller.product;

import static com.ing.store.utils.Constants.DELETED_PRODUCT;

import com.ing.store.entity.product.Product;
import com.ing.store.service.product.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

  @GetMapping("/name/{name}")
  public ResponseEntity<Product> findProductByName(@PathVariable String name) {
    return ResponseEntity.ok(productService.findProductByName(name));
  }

  @GetMapping
  public ResponseEntity<List<Product>> retrieveAllProducts() {
    return ResponseEntity.ok(productService.retrieveAllProducts());
  }

  @PutMapping("/{id}/price")
  public ResponseEntity<Product> updateProductPrice(@PathVariable Integer id, @RequestParam Double price) {
    return ResponseEntity.ok(productService.updateProductPrice(id, price));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
    productService.deleteProduct(id);
    return ResponseEntity.ok(DELETED_PRODUCT.concat(id.toString()));
  }
}
