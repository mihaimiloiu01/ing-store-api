package com.ing.store.service.product;

import com.ing.store.entity.product.Product;
import com.ing.store.exception.models.ProductNotFoundException;
import com.ing.store.repository.product.ProductRepository;
import com.ing.store.utils.Constants;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ProductService {
  private ProductRepository productRepository;

  public Product addProduct(Product product) {
    log.info(Constants.ADDING_PRODUCT_LOG_MESSAGE, product);
    return productRepository.save(product);
  }

  public Product findProductById(Integer id) {
    log.info(Constants.FINDING_PRODUCT_LOG_MESSAGE, id);
    return productRepository.findProductById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }

  public List<Product> retrieveAllProducts() {
    log.info(Constants.RETRIEVE_ALL_PRODUCTS_LOG_MESSAGE);
    return productRepository.findAll();
  }

  public Product findProductByName(String name) {
    return productRepository.findProductByName(name).orElseThrow(() -> new ProductNotFoundException(name));
  }

  public Product updateProductPrice(Integer id, Double newPrice) {
    log.info(Constants.UPDATING_PRICE_LOG_MESSAGE, id, newPrice);
    Product product = productRepository.findProductById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));
    product.setPrice(newPrice);
    return productRepository.save(product);
  }

  public void deleteProduct(Integer id) {
    productRepository.deleteProductById(id);
  }
}
