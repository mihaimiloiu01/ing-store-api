package com.ing.store.exception;

public class ExistingCredentialException extends RuntimeException {
  public ExistingCredentialException(String message) {
    super(message);
  }
}
