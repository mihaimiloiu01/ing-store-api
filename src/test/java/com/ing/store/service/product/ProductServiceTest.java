package com.ing.store.service.product;

import static org.mockito.Mockito.when;

import com.ing.store.entity.product.Product;
import com.ing.store.exception.models.ProductNotFoundException;
import com.ing.store.repository.product.ProductRepository;
import com.ing.store.utils.TestUtils;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
  @Mock
  private ProductRepository productRepository;
  @InjectMocks
  private ProductService productService;

  @Test
  void testAddProduct() {
    when(productRepository.save(Mockito.any())).thenReturn(TestUtils.generateProduct());
    Product registeredProduct = productService.addProduct(TestUtils.generateProduct());

    Assertions.assertEquals(registeredProduct.getName(), TestUtils.generateProduct().getName());
  }

  @Test
  void testFindProductByName() {
    when(productRepository.findProductByName(Mockito.any())).thenReturn(
        Optional.of(TestUtils.generateProduct()));
    Product fetchedProduct = productService.findProductByName(TestUtils.generateProduct()
        .getName());

    Assertions.assertEquals(fetchedProduct.getCurrency(), TestUtils.generateProduct().getCurrency());
  }

  @Test
  void testFindProductById() {
    when(productRepository.findProductById(Mockito.any())).thenReturn(
        Optional.of(TestUtils.generateProduct()));
    Product fetchedProduct = productService.findProductById(1);

    Assertions.assertEquals(fetchedProduct.getCurrency(), TestUtils.generateProduct().getCurrency());
  }

  @Test
  void testRetrieveAllProducts() {
    when(productRepository.findAll()).thenReturn(List.of(TestUtils.generateProduct()));

    List<Product> products = productService.retrieveAllProducts();

    Assertions.assertNotNull(products);
  }

  @Test
  void testFindProductByName_ProductNotFound() {
    when(productRepository.findProductByName(Mockito.any()))
        .thenThrow(new ProductNotFoundException(TestUtils.generateProduct().getName()));

    Assertions.assertThrows(ProductNotFoundException.class, () -> {
      productService.findProductByName(TestUtils.generateProduct()
          .getName());
    });
  }

  @Test
  void testUpdateProductPrice() {
    int productId = 1;
    double newPrice = 99.99;
    Product existingProduct = new Product();
    existingProduct.setId(productId);
    existingProduct.setPrice(50.0);

    when(productRepository.findProductById(productId)).thenReturn(Optional.of(existingProduct));
    when(productRepository.save(existingProduct)).thenReturn(existingProduct);

    Product updatedProduct = productService.updateProductPrice(productId, newPrice);

    Assertions.assertEquals(newPrice, updatedProduct.getPrice(), 0.01);
    Mockito.verify(productRepository, Mockito.times(1)).findProductById(productId);
    Mockito.verify(productRepository, Mockito.times(1)).save(existingProduct);
  }

  @Test
  void testUpdateProductPrice_ProductNotFound() {
    int productId = 1;
    double newPrice = 99.99;

    when(productRepository.findProductById(productId))
        .thenThrow(new ProductNotFoundException("Product not found"));

    Assertions.assertThrows(ProductNotFoundException.class, () -> {
      productService.updateProductPrice(1, newPrice);
    });
  }

  @Test
  void testDeleteProduct() {
    int productId = 1;

    productService.deleteProduct(productId);
    Mockito.verify(productRepository, Mockito.times(1)).deleteProductById(productId);
  }
}
