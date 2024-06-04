package com.ing.store.exception.models;

public class ExistingCredentialException extends RuntimeException {
  public ExistingCredentialException(String message) {
    super(message);
  }
}
