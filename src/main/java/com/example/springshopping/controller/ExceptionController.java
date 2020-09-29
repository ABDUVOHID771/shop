package com.example.springshopping.controller;

import com.example.springshopping.exception.*;
import com.example.springshopping.exception.InternalError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExceptionController extends ResponseEntityExceptionHandler {

  @ExceptionHandler
  public final ResponseEntity<?> handleUsernameDuplicatedException(
      UsernameException usernameAlreadyExists, WebRequest webRequest) {
    UsernameAlreadyExists exists = new UsernameAlreadyExists(usernameAlreadyExists.getMessage());
    return new ResponseEntity<>(exists, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public final ResponseEntity<?> handleIllegalExceptiuon(
      IllegalException exception, WebRequest webRequest) {
    AnyError error = new AnyError(exception.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public final ResponseEntity<?> handleNotFound(
      ResourceNotFoundException exception, WebRequest webRequest) {
    AnyError error = new AnyError(exception.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public final ResponseEntity<?> handleInternalError(
      InternalError exception, WebRequest webRequest) {
    AnyError error = new AnyError(exception.getMessage());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
