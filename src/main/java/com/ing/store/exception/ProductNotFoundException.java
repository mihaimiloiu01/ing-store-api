package com.ing.store.exception;

import com.ing.store.utils.Constants;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(UUID id) {
    super(Constants.PRODUCT_NOT_FOUND_EXCEPTION + id);
  }
}
