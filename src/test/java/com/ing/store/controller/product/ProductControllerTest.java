package com.ing.store.controller.product;

import static org.mockito.Mockito.when;

import com.ing.store.entity.product.Product;
import com.ing.store.service.product.ProductService;
import com.ing.store.utils.TestUtils;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
  @Mock
  private ProductService productService;

  @InjectMocks
  private ProductController productController;

  @Test
  void testAddProduct() {
    when(productService.addProduct(Mockito.any())).thenReturn(TestUtils.generateProduct());

    ResponseEntity<Product> response = productController.addProduct(TestUtils.generateProduct());

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(TestUtils.generateProduct().getName(), response.getBody().getName());
  }

  @Test
  void testFindProductById() {
    int productId = 1;
    when(productService.findProductById(productId)).thenReturn(TestUtils.generateProduct());

    ResponseEntity<Product> response = productController.findProductById(productId);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(TestUtils.generateProduct(), response.getBody());
  }

  @Test
  void testFindProductByName() {
    String mockProductName = "MockedProductName";
    when(productService.findProductByName(mockProductName)).thenReturn(TestUtils.generateProduct());

    ResponseEntity<Product> response = productController.findProductByName(mockProductName);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(TestUtils.generateProduct(), response.getBody());
  }

  @Test
  void testRetrieveAllProducts() {
    when(productService.retrieveAllProducts()).thenReturn(List.of(TestUtils.generateProduct()));

    ResponseEntity<List<Product>> response = productController.retrieveAllProducts();

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void updateProductPrice() {
    when(productService.updateProductPrice(Mockito.any(), Mockito.any())).thenReturn(TestUtils.generateProduct());

    ResponseEntity<Product> response = productController.updateProductPrice(1, 100.00);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void testDeleteProduct() {
    int productId = 1;
    ResponseEntity<String> response = productController.deleteProduct(productId);

    Mockito.verify(productService, Mockito.times(1)).deleteProduct(productId);
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("Deleted product with id: " + productId, response.getBody());
  }
}
