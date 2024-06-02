package com.ing.store.exception;

import com.ing.store.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(Integer id) {
    super(Constants.PRODUCT_NOT_FOUND_EXCEPTION + id);
  }

  public ProductNotFoundException(String name) {
    super(Constants.PRODUCT_NOT_FOUND_EXCEPTION + name);
  }
}
