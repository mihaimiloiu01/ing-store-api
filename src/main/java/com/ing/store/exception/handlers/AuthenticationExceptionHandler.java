package com.ing.store.exception.handlers;

import com.ing.store.exception.models.ErrorDetails;
import com.ing.store.exception.models.ExistingCredentialException;
import com.ing.store.exception.models.InvalidPasswordException;
import com.ing.store.exception.models.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AuthenticationExceptionHandler {
  @ExceptionHandler(ExistingCredentialException.class)
  public ResponseEntity<?> handleExistingCredentialException(ExistingCredentialException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidPasswordException.class)
  public ResponseEntity<?> handleInvalidpasswordException(InvalidPasswordException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
}
